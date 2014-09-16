package cn.mob.anlystic.master

import akka.actor._
import cn.mob.anlystic.{NodeStateManager, NodeState}

/**
 * @version 1.0 date : 2014/9/15
 * @author : Dempe 
 */
class Master extends Actor {

  def receive = {
    case state: NodeState => {
      println("===>"+state.path)
      NodeStateManager.put(state.path, state.state)
    }


  }

}
