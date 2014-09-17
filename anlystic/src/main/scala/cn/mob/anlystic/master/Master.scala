package cn.mob.anlystic.master

import akka.actor._
import cn.mob.anlystic.cluster.{NodeMananger, Node}

/**
 * @version 1.0 date : 2014/9/15
 * @author : Dempe 
 */
class Master extends Actor {

  def receive = {
    case node: Node => {

      println(node.getHostname + ":" + node.getPort + " node heart beat")
      NodeMananger.put(node);
    }


  }

}
