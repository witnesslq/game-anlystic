package cn.mob.anlystic

import akka.actor.{Props, ActorSystem}
import akka.routing.FromConfig
import cn.mob.anlystic.worker.AnalysisAcotor
import com.typesafe.config.ConfigFactory

/**
 * @version 1.0 date : 2014/9/15
 * @author : Dempe 
 */
object MasterMain extends App{

  val system = ActorSystem("anlystic-master",ConfigFactory.load("application"))
  val router = system.actorOf(Props[AnalysisAcotor].withRouter(FromConfig()),"routees")

  router ! "hello master message"


}
