package cn.mob.anlystic.worker

import akka.actor.Actor
import cn.mob.anlystic.util.Utils

/**
 * 负责解压并清洗不规范数据
 * @version 1.0 date : 2014/9/12
 * @author : Dempe 
 */
class UnzipActor extends Actor {


  def receive = {

  }


  def unzip(message: String): String = {
    Utils.unzip(message)
  }

}
