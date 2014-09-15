package cn.mob.anlystic.worker

import akka.actor.Actor

/**
 * 处理数据统计分析核心业务
 * @version 1.0 date : 2014/9/12
 * @author : Dempe 
 */
class AnalysisAcotor extends Actor {

  def receive = {

    case  msg :String =>{
      println(msg)

    }
    //
//    LaunchAnalysis.process
//    PageAnalysis.process
//    EventAnalysis.process
  }

}
