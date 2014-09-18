package cn.mob.node

import scala.concurrent.duration._

/**
 * @version 1.0 date : 2014/9/18
 * @author : Dempe 
 */
object HeadNodeServer extends App {

  val nodeConf = new NodeConf()
  // 默认无下游节点, 即默认为head node
  nodeConf.setLocalNode("node@127.0.0.1:2701")

  val context = new NodeContext(nodeConf)

  context.start()


  import context.system.dispatcher

  val stat: MetricsStat = MetricsStat.getInstance()

  //每隔1s向下游节点发送一次数据
  var count: Int = 0
  while (true) {
    context.job ! "this is the head node msg"
    stat.incrementAndGet()
    count = count + 1
    if (count % 10000 == 0) {
      Thread.sleep(100)
    }

  }
  context.system.scheduler.schedule(0 milliseconds, 1000 milliseconds) {
    context.job ! "this is the head node msg"
  }


}
