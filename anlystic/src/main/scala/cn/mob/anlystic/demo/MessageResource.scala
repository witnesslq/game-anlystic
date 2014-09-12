package cn.mob.anlystic.demo

import com.alibaba.fastjson.{JSON, JSONObject}
import scala.io.Source

/**
 * @version 1.0 date : 2014/9/3
 * @author : Dempe 
 */
object MessageResource {

  def readStringMsg(fileName: String): String = {
    val strBuf: StringBuffer = new StringBuffer()
    Source.fromFile(fileName).getLines().foreach(value => {
      strBuf.append(value)
    });
    JSON.parse(strBuf.toString).toString
  }

  def readJSONMsg(fileName: String): JSONObject = {
    val strMsg = readStringMsg(fileName)
    JSON.parseObject(strMsg)
  }

}
