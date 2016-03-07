package com.dempe.anlystic

/**
 * Created with IntelliJ IDEA. 
 * User: Dempe 
 * Date: 2016/3/7 
 * Time: 17:32 
 * To change this template use File | Settings | File Templates. 
 */
object Registration extends Serializable

trait EventMessage extends Serializable
case class RawNginxRecord(sourceHost: String, line: String) extends EventMessage
case class NginxRecord(sourceHost: String, eventCode: String, line: String) extends EventMessage
case class FilteredRecord(sourceHost: String, eventCode: String, line: String, logDate: String, realIp: String) extends EventMessage