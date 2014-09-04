import akka.actor.Actor

/**
 * @version 1.0 date : 2014/9/4
 * @author : Dempe 
 */
class TestActor extends Actor{

  var count:Int = 0
  def receive = {
    case msg :String =>{
     // println("msg===>" + msg)
      count = count +1
      if (count%1000000==0){
        println("count===>"+count)
      }

    }
  }

}
