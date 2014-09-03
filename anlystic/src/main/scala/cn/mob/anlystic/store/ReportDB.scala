package cn.mob.anlystic.store

import java.util.concurrent.ConcurrentHashMap
import java.util.HashMap

/**
 * @version 1.0 date : 2014/9/3
 * @author : Dempe 
 */
object ReportDB {

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

  def hincrBy(key: String, field: String) = {
    var fieldMap = reportMap.get(key);
    if (fieldMap != null) {
      val num = fieldMap.get(field)
      fieldMap.put(key, num + 1)
    } else {
      fieldMap = new HashMap[String, Int](10)
      fieldMap.put(field, 1)
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
