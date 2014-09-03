package cn.mob.anlystic

import akka.actor.{Props, ActorSystem}

/**
 * @version 1.0 date : 2014/9/2
 * @author : Dempe 
 */
object Main extends App {

  val system = ActorSystem("anlystic");
  val receiveStreamActor = system.actorOf(Props[ReceiveStreamActor], name = "receiveStreamActor")
  val msg = MessageResource.readStringMsg(R.fileName)
  receiveStreamActor ! msg

  //system.shutdown()


}
