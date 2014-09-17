package cn.mob.node

import akka.actor.Actor

/**
 * 获取下游服务心跳
 * @version 1.0 date : 2014/9/17
 * @author : Dempe 
 */
class HeartbeatActor extends Actor {

  def receive = {
    case node: Node => {
      println("heatbeat>>>"+node)
      NodeMananger.put(node)
    }
  }

}
