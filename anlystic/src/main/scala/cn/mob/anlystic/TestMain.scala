package cn.mob.anlystic

import com.typesafe.config.ConfigFactory

/**
 * @version 1.0 date : 2014/9/15
 * @author : Dempe 
 */
object TestMain extends App{

  val conf = ConfigFactory.parseString("akka.actor.deployment.target.nodes="+
    "['akka.tcp://anlystic-worker@192.168.1.144:2554']").withFallback(ConfigFactory.load("application"))

  println(conf)
}
