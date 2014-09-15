package cn.mob.anlystic.master

import akka.actor.Actor
import cn.mob.anlystic.{Conf, WorkerStat}

/**
 * @version 1.0 date : 2014/9/15
 * @author : Dempe 
 */
class Master extends Actor{

  def receive = {
    case stat : WorkerStat =>{
      val uri = stat.hostname + ":" + stat.port
      Conf.uris+=uri
      println("uri===>"+uri)
    }
  }

}
