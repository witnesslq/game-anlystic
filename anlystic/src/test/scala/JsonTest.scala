import cn.mob.anlystic.MessageResource

/**
 * @version 1.0 date : 2014/9/3
 * @author : Dempe 
 */
object JsonTest extends App {

  val fileName = "F:\\IdeaProjects\\game-anlystic\\anlystic\\src\\main\\resources\\message.json";
  //  val result  = Source.fromFile(new File(fileName)).getLines().foreach(println)
  //  println(result)
  val jsonMessage = MessageResource.readJSONMsg(fileName)
  println(jsonMessage)
  val appkey = jsonMessage.getString("appkey")
  println(appkey)

}
