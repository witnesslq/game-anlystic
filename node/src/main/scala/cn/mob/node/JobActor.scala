package cn.mob.node

import akka.actor.{ActorPath, ActorSelection, Actor}
import java.util.concurrent.ConcurrentHashMap

/**
 * 执行job 并将消息分发到下游服务
 * 【可扩展通过多种策略分发，目前通过一致哈希算法分发】
 * undo mailbox持久化到本地file
 * undo 抽象出业务逻辑和分发代码
 * undo 抽象出分发策略
 * @version 1.0 date : 2014/9/17
 * @author : Dempe 
 */
class JobActor extends Actor {

  val jobPath = "/" + Constants.USER + "/" + Constants.JOB

  def receive = {
    case msg: String => {

      //处理业务逻辑
      val result = doWork(msg)

      //分发到下个节点
      distribution(result)

    }
  }

  def doWork(msg: String): String = {
    println("do job ing")
    msg
  }

  def distribution(msg: String) = {
    val add  = HashedUtil.getNodeByKey(msg)
    if (add != null) {
      val actor = WorkerPool.getAndSet(add.toString + jobPath)
      actor ! msg
    }else{
      println("the next node is null")
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

