package cn.mob.node

import akka.actor.{ActorPath, Props, ActorSystem}
import scala.concurrent.duration._
import com.typesafe.config.ConfigFactory

/**
 * @version 1.0 date : 2014/9/17
 * @author : Dempe 
 */
object NodeServer extends App {

  val nodeConf = new NodeConf()

  val node = nodeConf.getLocalNode

  var conf = ConfigFactory.parseString("akka.remote.netty.tcp.port="+node.getPort).withFallback(ConfigFactory.load("common"))
  val system = ActorSystem(node.getNodename,conf)

  //启动心跳和job actor
  system.actorOf(Props[HeartbeatActor], "heartbeat")
  system.actorOf(Props[JobActor], "job")


  val preNodes: java.util.List[Node] = nodeConf.getPreNodes()
  val preNodeActors = preNodes.toArray.map(node => {
    val path = node.toString + "/user/heartbeat"
    println("===>" + path)
    system.actorSelection(ActorPath.fromString(path))
  })

  val localNode = nodeConf.getLocalNode

  import system.dispatcher

  //通知上个节点 本节点可用
  //每隔1s向master发送心跳
  system.scheduler.schedule(0 milliseconds, 1000 milliseconds) {
    preNodeActors.foreach(actor => actor ! localNode)
  }


}
