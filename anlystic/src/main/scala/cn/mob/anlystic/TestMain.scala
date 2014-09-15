package cn.mob.anlystic

import com.typesafe.config.ConfigFactory

/**
 * @version 1.0 date : 2014/9/15
 * @author : Dempe 
 */
object TestMain extends App{
   val conf = ConfigFactory.load("work")
  val iter = conf.entrySet().iterator()
  while (iter.hasNext){
    println(iter.next())
  }

  println(conf.getString("akka.remote.netty.tcp.port"))
  println(conf.getString("akka.remote.netty.tcp.hostname"))


}
