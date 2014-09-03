package cn.mob.anlystic

import akka.actor.{Props, ActorLogging, Actor}
import com.alibaba.fastjson.{JSONObject, JSONArray, JSON}

/**
 * @version 1.0 date : 2014/9/2
 * @author : Dempe 
 */

case class LaunchDataTuple(deviceId: String, channel: String, version: String, model: String, sysver: String, os: String,
                           network: String, country: String, province: String, launchData: JSONArray)

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
      if (validator(jsonMsg)) {
        val deviceData = jsonMsg.getJSONObject(R.device_data)

        val deviceId = deviceData.getString(R.device_id)
        val version = deviceData.getString(R.version)
        val channel = deviceData.getString(R.channel)
        val model = deviceData.getString(R.model)
        val sysver = deviceData.getString(R.sysver)
        val os = deviceData.getString(R.os)
        val network = deviceData.getString(R.network)
        val country = deviceData.getString(R.country)
        val province = deviceData.getString(R.province)

        val launchData = jsonMsg.getJSONArray(R.launch_data)
        val pageData = jsonMsg.getJSONArray(R.page_data)
        val exitData = jsonMsg.getJSONArray(R.exit_data)

        launchActor ! LaunchDataTuple(deviceId, channel, version, model, sysver, os, network, country, province, launchData)
        pageActor ! PageDataTuple(deviceId, channel, version, pageData)
        exitActor ! ExitDataTuple(deviceId, channel, version, exitData)

      } else {
        println("error msg")
      }


    }
  }

  def validator(json: JSONObject): Boolean = {
    true;
  }

}
