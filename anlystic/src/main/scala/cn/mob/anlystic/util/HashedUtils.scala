package cn.mob.anlystic.util

import java.util.List
import cn.mob.anlystic.{NodeState, NodeStateManager}

/**
 * @version 1.0 date : 2014/9/16
 * @author : Dempe 
 */
object HashedUtils {
  private var aliveNodeList: List[NodeState] = NodeStateManager.getAliveNodeList

  /**
   * 通过一致hash算法获取key和host映射关系
   *
   * @param key
   * @return
   */
  def getHostByKey(key: String): String = {
    return "192.168.1.144:2553"
  }

  def reload = {
    aliveNodeList = NodeStateManager.getAliveNodeList
  }

}
