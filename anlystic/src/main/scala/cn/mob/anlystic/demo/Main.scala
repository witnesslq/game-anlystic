package cn.mob.anlystic.demo

import akka.actor.{Props, ActorSystem}
import scala.concurrent.duration._
import com.typesafe.config.ConfigFactory
import cn.mob.anlystic.R

/**
 * @version 1.0 date : 2014/9/2
 * @author : Dempe 
 */
object Main extends App {

  val system = ActorSystem("anlystic", ConfigFactory.load("app"));
  val receiveStreamActor = system.actorOf(Props[ReceiveStreamActor].withMailbox("bounded-mailbox"), name = "receiveStreamActor")
  val msg = MessageResource.readStringMsg(R.fileName)
  var num: Int = 0;
  //Use the system’s dispatcher as ExecutionContext

  import system.dispatcher

  system.scheduler.schedule(0 milliseconds, 100 milliseconds) {
    (1 to 1000000).foreach(v => receiveStreamActor ! msg)
  }

  //  while (true) {
  //    Thread.sleep(5000)
  //    ReportDB.sync
  //  }
  //
  //  system.shutdown()

}
