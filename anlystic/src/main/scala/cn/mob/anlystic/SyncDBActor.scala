package cn.mob.anlystic

import akka.actor.Actor
import java.util.concurrent.ConcurrentHashMap
import java.util.HashMap

/**
 * 收集各节点发送上来的数据，reduceByKey
 * 定期同步数据到mongodb，
 * 数据同步actor可有多个，提高同步效率
 * @version 1.0 date : 2014/9/12
 * @author : Dempe 
 */
class SyncDBActor extends Actor {

  def receive = {
    case cmd: String => {


    }
  }

}

object MapDB {
  val reportMap: ConcurrentHashMap[String, HashMap[String, Int]] = new ConcurrentHashMap[String, HashMap[String, Int]]()

  def hincrByValue(key: String, field: String, value: Int) = {
    var fieldMap = reportMap.get(key);
    if (fieldMap != null) {
      val num = fieldMap.get(field)
      fieldMap.put(key, num + value)
    } else {
      fieldMap = new HashMap[String, Int](10)
      fieldMap.put(field, value)
    }
    reportMap.put(key, fieldMap)
  }


  def sync = {
    println("===>sync now")
    val iter = reportMap.keySet().iterator()
    while (iter.hasNext) {
      println(iter.next())
    }

  }
}
