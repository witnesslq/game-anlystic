package cn.mob.node


/**
 * @version 1.0 date : 2014/9/17
 * @author : Dempe 
 */
object NodeServer extends App {

  val nodeConf = new NodeConf()
  val context = new NodeContext("node1@127.0.0.1:2701","node@127.0.0.1:2700",nodeConf)


  context.start()


}
