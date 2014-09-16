package cn.mob.anlystic

import java.util.concurrent.ConcurrentHashMap

/**
 * @version 1.0 date : 2014/9/16
 * @author : Dempe 
 */
sealed trait State

case object Alive extends State

case object Dead extends State

object NodeStateManager {
  var nodes = new ConcurrentHashMap[String, State]()

  def isAlive(path: String) :Boolean={
    var flag = false;
    nodes.get(path) match {
      case Alive => flag = true;
      case Dead =>
    }
    flag
  }

  def put(path: String, state: State) = {
    nodes.put(path, state)
  }

  def remove(path: String) = {
    nodes.remove(path)
  }

  def getAliveNodeList: List[NodeState] = {
    val nodesList = List[NodeState]()
    val iter: java.util.Iterator[java.util.Map.Entry[String, State]] = nodes.entrySet().iterator()
    while (iter.hasNext) {
      val entry = iter.next()
      nodesList -> NodeState(entry.getKey, entry.getValue)
    }
    nodesList
  }

}

case class NodeState(path: String, state: State)
