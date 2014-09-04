import akka.actor.{Props, ActorSystem}

/**
 * @version 1.0 date : 2014/9/4
 * @author : Dempe 
 */
object MainTest extends App {

  val system = ActorSystem("anlystic");
  val testActor = system.actorOf(Props[TestActor], name = "testActor")

  while (true) {
    testActor ! "hello"
  }

}
