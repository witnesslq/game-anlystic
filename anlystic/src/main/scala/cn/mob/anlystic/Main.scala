package cn.mob.anlystic

import akka.actor.{Props, ActorSystem}
import cn.mob.anlystic.store.ReportDB

/**
 * @version 1.0 date : 2014/9/2
 * @author : Dempe 
 */
object Main extends App {

  val system = ActorSystem("anlystic");
  val receiveStreamActor = system.actorOf(Props[ReceiveStreamActor], name = "receiveStreamActor")
  val msg = MessageResource.readStringMsg(R.fileName)
  receiveStreamActor ! msg



  while (true) {
    Thread.sleep(5000)
    ReportDB.sync
  }

  system.shutdown()

}
