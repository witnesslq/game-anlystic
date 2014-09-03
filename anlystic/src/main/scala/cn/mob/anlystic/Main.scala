package cn.mob.anlystic

import akka.actor.{Props, ActorSystem}

/**
 * @version 1.0 date : 2014/9/2
 * @author : Dempe 
 */
object Main extends App{
  val system = ActorSystem("anlystic");
  val receiveStreamActor = system.actorOf(Props[ReceiveStreamActor],name="receiveStreamActor")
  receiveStreamActor ! "hello"


}
