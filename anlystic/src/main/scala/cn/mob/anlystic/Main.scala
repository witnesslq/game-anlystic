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
  var num: Int = 0;
  while (true) {
    receiveStreamActor ! msg
    num = num + 1;
    if (num % 10000 == 0) {
      Thread.sleep(100)

    }
  }




  while (true) {
    Thread.sleep(5000)
    ReportDB.sync
  }

  system.shutdown()

}
