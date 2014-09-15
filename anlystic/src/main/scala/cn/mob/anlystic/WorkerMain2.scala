package cn.mob.anlystic

import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory
import cn.mob.anlystic.worker.AnalysisAcotor
import akka.routing.FromConfig
import scala.concurrent.duration._

/**
  * @version 1.0 date : 2014/9/15
  * @author : Dempe
  */
object WorkerMain2 extends  App{

   val system = ActorSystem("anlystic-worker",ConfigFactory.load("worker2"))
   val router = system.actorOf(Props[AnalysisAcotor].withRouter(FromConfig()),"routees")

//  val addresses = Seq(
//    Address("akka.tcp", "remotesys", "otherhost", 1234),
//    AddressFromURIString("akka.tcp://othersys@anotherhost:1234"))


 }
