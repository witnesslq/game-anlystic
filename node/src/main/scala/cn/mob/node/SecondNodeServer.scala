package cn.mob.node


/**
 * @version 1.0 date : 2014/9/17
 * @author : Dempe 
 */
object SecondNodeServer extends App {

  val nodeConf = new NodeConf()
  nodeConf.setLocalNode("node@127.0.0.1:2702")
  // 支持多个上游节点，用逗号隔开
  nodeConf.setPreNodes("node@127.0.0.1:2701")

  val context = new NodeContext(nodeConf)

  context.start()



}

