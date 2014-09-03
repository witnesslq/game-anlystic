package cn.mob.anlystic

import akka.actor.{ActorLogging, Actor}

/**
 * @version 1.0 date : 2014/9/2
 * @author : Dempe 
 */
class PageActor extends Actor with ActorLogging {
  def receive = {
    case msg: String => {
      //
      log.info("page====>" + msg)

    }

    case tuple: PageDataTuple => {
      // println("page tuple===>" + tuple)
    }
    case _ => {

    }
  }
}
