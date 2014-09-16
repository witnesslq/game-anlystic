package cn.mob.anlystic.worker

import akka.actor.{Props, Actor}
import cn.mob.anlystic.util.Utils

/**
 * 负责解压并清洗不规范数据
 * @version 1.0 date : 2014/9/12
 * @author : Dempe 
 */
class UnzipActor extends Actor {

  val anlysisActor = context.actorOf(Props[AnalysisAcotor], "analysis")

  def receive = {

    case msg: String => {

    }

  }


  def unzip(message: String): String = {
    Utils.unzip(message)
  }

}
