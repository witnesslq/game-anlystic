package cn.mob.anlystic

import akka.actor.{Address, AddressFromURIString, Props, ActorSystem}
import akka.routing.{RoundRobinRouter, FromConfig}
import cn.mob.anlystic.worker.AnalysisAcotor
import com.typesafe.config.ConfigFactory
import scala.concurrent.duration._
import akka.remote.routing.RemoteRouterConfig

/**
 * @version 1.0 date : 2014/9/15
 * @author : Dempe 
 */
object MasterMain extends App{

  val conf = ConfigFactory.load("application")
  val system = ActorSystem("anlystic-master",conf)

  val ports = Seq(2553,2554,2555)

  val adds = ports.map(port=>{
    //Address("akka.tcp", "anlystic-worker", "192.168.1.144", port)
    AddressFromURIString("akka.tcp://anlystic-worker@192.168.1.144:"+port)
  })

  //val router = system.actorOf(Props[AnalysisAcotor].withRouter(FromConfig()),"routees")
  val router = system.actorOf(Props[AnalysisAcotor].withRouter(
    RemoteRouterConfig(RoundRobinRouter(5), adds)))

  import system.dispatcher

  system.scheduler.schedule(0 milliseconds, 1000 milliseconds) {
    (1 to 3).foreach(v => router ! "hello master message")
  }





}
