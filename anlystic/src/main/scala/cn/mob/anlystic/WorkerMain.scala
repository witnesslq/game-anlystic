package cn.mob.anlystic

import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory
import cn.mob.anlystic.worker.AnalysisAcotor
import cn.mob.anlystic.master.Master
import scala.concurrent.duration._
import cn.mob.anlystic.cluster.Node

/**
 * @version 1.0 date : 2014/9/15
 * @author : Dempe 
 */
object WorkerMain extends App {

  val conf = ConfigFactory.load("worker")
  val system = ActorSystem("anlystic-worker", conf)
  conf.getConfig("akka.remote")

  val master = system.actorOf(Props[Master], "master")

  val worker = system.actorOf(Props[AnalysisAcotor], "analysisActor")

  val port = conf.getString("akka.remote.netty.tcp.port").toInt
  val hostname = conf.getString("akka.remote.netty.tcp.hostname")

  import system.dispatcher

  //每隔1s向master发送心跳
  system.scheduler.schedule(0 milliseconds, 1000 milliseconds) {

    master ! new Node(hostname, port)
  }

}

