package cn.mob.anlystic

import akka.actor._
import cn.mob.anlystic.util.MetricsStat

/**
 * @version 1.0 date : 2014/9/2
 * @author : Dempe 
 */
class ReceiveStreamActor extends Actor with ActorLogging {

  val unzipActor = context.actorOf(Props[UnzipActor], name = "unzip")

  val metricsStat = MetricsStat.getInstance();

  override def preStart(): Unit = {

    println("start receive stream actor now")
  }

  override def unhandled(message: Any): Unit = {
    println("handled unhandled message")
    super.unhandled(message)
  }

  def receive = {
    case msg: String => {
      //
      metricsStat.incrementAndGet();
      // log.info("receive====>" + msg)
      unzipActor ! msg;

    }
    case _ => {

    }
  }

}
