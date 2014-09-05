package cn.mob.anlystic

import akka.actor.{Props, ActorLogging, Actor}

/**
 * @version 1.0 date : 2014/9/2
 * @author : Dempe 
 */
class UnzipActor extends Actor with ActorLogging {

  val distributeActor = context.actorOf(Props[DistributeActor].withMailbox("bounded-mailbox"), name = "distribute")

  override def preStart(): Unit = {
    println("start unzip actor now")
  }

  def receive = {
    case msg: String => {
      //
      //log.info("unzip====>" + msg)
      distributeActor ! msg

    }
    case _ => {

    }
  }
}
