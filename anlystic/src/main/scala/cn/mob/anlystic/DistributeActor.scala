package cn.mob.anlystic

import akka.actor.{Props, ActorLogging, Actor}
import com.alibaba.fastjson.{JSON, JSONObject}

/**
 * @version 1.0 date : 2014/9/2
 * @author : Dempe 
 */
class DistributeActor extends Actor with ActorLogging {

  val pageActor = context.actorOf(Props[PageActor], name = "page")
  val exitActor = context.actorOf(Props[ExitActor], name = "exit")
  val launchActor = context.actorOf(Props[LaunchActor], name = "launch")

  def receive = {
    case msg: String => {
      //
      log.info("distribute====>" + msg)
      val jsonMsg = JSON.parseObject(msg).getJSONObject(R.m)
      val deviceData = jsonMsg.getJSONObject(R.device_data)

      val deviceId = deviceData.getString(R.device_id)
      val version = deviceData.getString(R.version)
      val channel = deviceData.getString(R.channel)
      val launchData = jsonMsg.getJSONArray(R.launch_data)
      val pageData = jsonMsg.getJSONArray(R.page_data)
      val exitData = jsonMsg.getJSONArray(R.exit_data)
      launchActor ! launchData.toJSONString
      pageActor ! pageData.toJSONString
      exitActor ! exitData.toJSONString


    }
  }

//  def validator(json: JSONObject) = {
//
//  }

}
