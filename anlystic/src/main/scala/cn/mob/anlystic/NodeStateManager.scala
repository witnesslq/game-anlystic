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

  def isAlive(path: String) {
    nodes.get(path) match {
      case Alive => true
      case Dead => false
    }
  }

  def put(path: String, state: State) = {
    nodes.put(path, state)
  }

  def remove(path: String) = {
    nodes.remove(path)
  }

  def getAliveNodeList: List[NodeState] = {
    val nodesList: java.util.ArrayList[NodeState] = new java.util.ArrayList[NodeState]()
    val iter: Iterator[java.util.Map.Entry[String, State]] = nodes.entrySet().iterator()
    while (iter.hasNext) {
      val entry = iter.next()
      NodeState(entry.getKey, entry.getValue)
      nodesList.add(NodeState)
    }
    nodesList
  }

}

case class NodeState(path: String, state: State)
