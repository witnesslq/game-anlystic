package cn.mob.anlystic.sample

import akka.actor.{ActorSystem, Actor, Props}
import akka.routing.RoundRobinRouter

/**
 * @version 1.0 date : 2014/9/5
 * @author : Dempe 
 */
object RootActor extends App {

  val system = ActorSystem("sample");
  val router1 = system.actorOf(Props[ExampleActor1].withRouter(
    RoundRobinRouter(nrOfInstances = 10)))

  1 to 10 foreach {
    i => router1 ! i.toString
  }


}

class ExampleActor1 extends Actor {
  def receive = {
    case msg: String => println("===>hello " + msg + " " + self.path.name)
  }
}
