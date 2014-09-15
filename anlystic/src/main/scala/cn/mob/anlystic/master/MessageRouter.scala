package cn.mob.anlystic.master

import akka.actor.{ActorRef, Props, SupervisorStrategy}
import akka.routing.{Router, RouterConfig}
import akka.dispatch.Dispatchers

/**
 * 负责动态分发消息到各节点
 * 根据算法 appkey映射到各个node，并发送
 * 动态的确定actor的数量
 * @version 1.0 date : 2014/9/12
 * @author : Dempe 
 */
class MessageRouter extends RouterConfig {


}
