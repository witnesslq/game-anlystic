package cn.mob.anlystic

import akka.actor.{ActorLogging, Actor}
import cn.mob.anlystic.store.ReportDB
import com.alibaba.fastjson.JSONObject

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
    case tuple: LaunchDataTuple => {
      println("launch tuple===>" + tuple)
      val launchDatas = tuple.launchData
      val key = tuple.version + R.split + tuple.channel;
      launchDatas.toArray().foreach(ld => {
        val createDate = ld.asInstanceOf[JSONObject].getString("create_date").substring(0, 10)

        val basekey = new StringBuffer(R.split).append(tuple.version).append(R.split).append(tuple.channel).
          append(R.split).append(createDate).toString
        ReportDB.hincrBy(R.usage_daily + basekey, R.drun)
        ReportDB.hincrBy(R.usage + R.split + createDate, R.drun)
        val deviceKey = basekey + R.split

        //device
        ReportDB.hincrBy(R.device_model + deviceKey + tuple.model, R.drun)
        ReportDB.hincrBy(R.device_resolution + deviceKey + tuple.sysver, R.drun)
        ReportDB.hincrBy(R.device_os + deviceKey + tuple.os, R.drun)
        ReportDB.hincrBy(R.device_network + deviceKey + tuple.network, R.drun)
        ReportDB.hincrBy(R.device_country + deviceKey + tuple.country, R.drun)
        ReportDB.hincrBy(R.device_province + deviceKey + tuple.province, R.drun)
      })


    }
  }
}
