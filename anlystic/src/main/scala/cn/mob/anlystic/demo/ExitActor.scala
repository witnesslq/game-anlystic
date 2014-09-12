package cn.mob.anlystic.demo

import akka.actor.{ActorLogging, Actor}

/**
 * @version 1.0 date : 2014/9/2
 * @author : Dempe 
 */
class ExitActor extends Actor with ActorLogging {
  def receive = {
    case msg: String => {
      //
      log.info("exit====>" + msg)
    }
    case tuple: ExitDataTuple => {
      //println("exit tuple===>" + tuple)
    }
    case _ => {

    }
  }
}
