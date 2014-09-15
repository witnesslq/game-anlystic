package cn.mob.anlystic

import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory
import cn.mob.anlystic.worker.AnalysisAcotor
import akka.routing.FromConfig

/**
 * @version 1.0 date : 2014/9/15
 * @author : Dempe 
 */
object WorkerMain extends  App{

  val system = ActorSystem("anlystic-worker",ConfigFactory.load("worker"))
  val router = system.actorOf(Props[AnalysisAcotor].withRouter(FromConfig()),"routees")


}
