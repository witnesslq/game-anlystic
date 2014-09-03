package cn.mob.anlystic

import akka.actor.{Props, ActorLogging, Actor}

/**
 * @version 1.0 date : 2014/9/2
 * @author : Dempe 
 */
class ReceiveStreamActor extends Actor with ActorLogging{

  val unzipActor = context.actorOf(Props[UnzipActor],name="unzip")

  override def preStart():Unit = {
    println("start receive stream actor now")
  }

  def receive = {
    case msg : String =>{
      //
      log.info("receive====>"+msg)
      unzipActor ! msg;

    }
  }

}
