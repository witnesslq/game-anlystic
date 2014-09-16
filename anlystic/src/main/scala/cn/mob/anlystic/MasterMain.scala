package cn.mob.anlystic

import akka.actor._
import com.typesafe.config.ConfigFactory
import cn.mob.anlystic.util.{Utils, ShareQueue}
import java.util.concurrent.ConcurrentHashMap

/**
 * @version 1.0 date : 2014/9/15
 * @author : Dempe 
 */
object MasterMain extends App {

  val conf = ConfigFactory.load("application")
  val system = ActorSystem("anlystic-master", conf)

  val queue: ShareQueue = new ShareQueue()

  while (true) {
    val appkeyAndDeviceId = queue.consume()
    // 必须为alive状态的worker
    val hostUrl = Utils.getHostByKey(appkeyAndDeviceId)
    println("hosturl===>"+hostUrl)
    val path = "akka.tcp://anlystic-worker@" + hostUrl + "/user/analysisActor"
    val worker: ActorSelection = WorkerPool.get(path)
    worker ! appkeyAndDeviceId

    Thread.sleep(5000)
  }

  object WorkerPool {
    val pool = new ConcurrentHashMap[String, ActorSelection]()

    def put(path: String, worker: ActorSelection) = {
      pool.put(path, worker)
    }

    def get(path: String):ActorSelection = {
      var worker = pool.get(path)
      if (worker == null) {
        worker = system.actorSelection(ActorPath.fromString(path))
        pool.put(path, worker)
      }
      worker
    }
  }


}
