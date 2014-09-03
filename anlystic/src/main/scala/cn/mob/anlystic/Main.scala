package cn.mob.anlystic

import akka.actor.{Props, ActorSystem}
import com.alibaba.fastjson.{JSON, JSONObject}

/**
 * @version 1.0 date : 2014/9/2
 * @author : Dempe 
 */
object Main extends App{

  val fileName = "F:\\IdeaProjects\\game-anlystic\\anlystic\\src\\main\\resources\\message.json";

  val system = ActorSystem("anlystic");
  val receiveStreamActor = system.actorOf(Props[ReceiveStreamActor],name="receiveStreamActor")
  val msg = MessageResource.readStringMsg(fileName)
  receiveStreamActor ! msg

  //system.shutdown()


}
