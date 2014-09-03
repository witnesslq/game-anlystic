package cn.mob.anlystic

import akka.actor.{ActorLogging, Actor}

/**
 * @version 1.0 date : 2014/9/2
 * @author : Dempe 
 */
class LaunchActor extends Actor with ActorLogging {
  def receive = {
    case msg: String => {
      //
      log.info("launch====>" + msg)

    }
    case tuple: LaunchDataTupe => {
      log.info("tuple-deviceId===>" + tuple.deviceId)
    }
  }
}
