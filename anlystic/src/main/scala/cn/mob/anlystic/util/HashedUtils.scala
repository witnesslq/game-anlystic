package cn.mob.anlystic.util

import cn.mob.anlystic.{NodeState, NodeStateManager}
import scala.util.Random

/**
 * @version 1.0 date : 2014/9/16
 * @author : Dempe 
 */
object HashedUtils {

  private var aliveNodeList: List[NodeState] = NodeStateManager.getAliveNodeList

  /**
   * 通过一致hash算法获取key和host映射关系
   * 暂未实现
   *
   * @param key
   * @return
   */
  def getHostByKey(key: String): String = {
    val ns = aliveNodeList.map(value=>value.path)
    ns(new Random().nextInt(ns.size))

  }

  def reload = {
    aliveNodeList = NodeStateManager.getAliveNodeList
  }

}
