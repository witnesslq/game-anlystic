package cn.mob.anlystic

import akka.actor.{Props, ActorLogging, Actor}
import com.alibaba.fastjson.{JSON, JSONObject}

/**
 * @version 1.0 date : 2014/9/2
 * @author : Dempe 
 */
class DistributeActor  extends Actor with ActorLogging{

  val pageActor = context.actorOf(Props[PageActor],name="page")
  val exitActor = context.actorOf(Props[ExitActor],name="exit")
  val launchActor = context.actorOf(Props[LaunchActor],name="launch")

  def receive = {
    case msg : String =>{
      //
      log.info("distribute====>"+msg)
      JSON.parseObject(msg)
      launchActor ! msg
      pageActor ! msg
      exitActor ! msg


    }
  }

  def validator(json:JSONObject) ={

  }

}
