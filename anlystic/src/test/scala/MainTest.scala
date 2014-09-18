import akka.actor.{ActorPath, Props, ActorSystem}

/**
 * @version 1.0 date : 2014/9/4
 * @author : Dempe 
 */
object MainTest extends App {

  val system = ActorSystem("anlystic");
  val remoteActor = system.actorSelection(ActorPath.fromString("akka.tcp://anlystic-master@192.168.1.144:2552"))

  remoteActor ! "hello"


}
