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



  //val router = system.actorOf(Props[AnalysisAcotor].withRouter(FromConfig()),"routees")

  import system.dispatcher

  while (Conf.uris.size==0){
    println("node is 0, sleep 1 second")
    Thread.sleep(1000)
  }
  val adds = Conf.uris.map(uri=>{
    //Address("akka.tcp", "anlystic-worker", "192.168.1.144", port)
    AddressFromURIString("akka.tcp://anlystic-worker@"+uri)
  })
  val router = system.actorOf(Props[AnalysisAcotor].withRouter(
    RemoteRouterConfig(RoundRobinRouter(5), adds)))


  system.scheduler.schedule(0 milliseconds, 1000 milliseconds) {
    (1 to 3).foreach(v => router ! "hello master message")
  }





}
