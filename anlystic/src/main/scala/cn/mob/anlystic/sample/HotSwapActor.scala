package cn.mob.anlystic.sample

import akka.actor.{Props, ActorSystem, Actor}

/**
 * @version 1.0 date : 2014/9/4
 * @author : Dempe 
 */
class HotSwapActor extends Actor {

  import context._

  def angry: Receive = {
    case "foo" => sender() ! "I am already angry?"
    case "bar" => become(happy)
  }

  def happy: Receive = {
    case "bar" => sender() ! "I am already happy :-)"
    case "foo" => become(angry)
  }

  def receive = {
    case "foo" => become(angry)
    case "bar" => become(happy)
  }
}

object Test extends App {
  val system = ActorSystem("anlystic");
  val testActor = system.actorOf(Props[HotSwapActor], name = "testActor")
  testActor ! "foo"
}