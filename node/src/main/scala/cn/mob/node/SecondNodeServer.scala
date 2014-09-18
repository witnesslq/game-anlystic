package cn.mob.node


/**
 * @version 1.0 date : 2014/9/17
 * @author : Dempe 
 */
object SecondNodeServer extends App {

  val nodeConf = new NodeConf()
  val context = new NodeContext("node@127.0.0.1:2702","node1@127.0.0.1:2701",nodeConf)

  context.start()

  context.job ! "hello"

  //  while (true){
  //    val msg = context.getMessage()
  //    println("msg===>"+msg)
  //  }


}

