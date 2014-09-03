package cn.mob.anlystic

import akka.actor.{Props, ActorLogging, Actor}
import com.alibaba.fastjson.{JSONArray, JSON}

/**
 * @version 1.0 date : 2014/9/2
 * @author : Dempe 
 */

case class LaunchDataTupe(deviceId: String, channel: String, version: String, launchData: String)

case class PageDataTuple(deviceId: String, channel: String, version: String, pageData: JSONArray)

case class ExitDataTuple(deviceId: String, channel: String, version: String, exitData: JSONArray)

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
      // launchActor ! launchData.toJSONString
      launchActor ! LaunchDataTupe(deviceId, channel, version, launchData.toJSONString)
      //pageActor ! pageData.toJSONString
      pageActor ! PageDataTuple(deviceId, channel, version, pageData)
      // exitActor ! exitData.toJSONString
      exitActor ! ExitDataTuple(deviceId, channel, version, exitData)


    }
  }

  //  def validator(json: JSONObject) = {
  //
  //  }

}
