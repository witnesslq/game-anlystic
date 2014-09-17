package cn.mob.node

import akka.actor.{ActorPath, ActorSelection, Actor}
import java.util.concurrent.ConcurrentHashMap

/**
 * 执行job 并将消息分发到下游服务
 * 【可扩展通过多种策略分发，目前通过一致哈希算法分发】
 * @version 1.0 date : 2014/9/17
 * @author : Dempe 
 */
class JobActor extends Actor {


  def receive = {
    case msg: String => {
      println("do job ing")
      val path = HashedUtil.getNodeByKey(msg) + "/user/job"
      val actor = WorkerPool.getAndSet(path)
      actor ! msg

    }
  }


  object WorkerPool {
    val pool = new ConcurrentHashMap[String, ActorSelection]()

    def put(path: String, worker: ActorSelection) = {
      pool.put(path, worker)
    }

    def getAndSet(path: String): ActorSelection = {
      var worker = pool.get(path)
      if (worker == null) {
        worker = context.actorSelection(ActorPath.fromString(path))
        pool.put(path, worker)
      }
      worker
    }
  }


}

