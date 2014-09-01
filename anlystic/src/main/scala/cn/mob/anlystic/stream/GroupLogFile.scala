package cn.mob.anlystic.stream

import akka.actor.ActorSystem
import akka.stream.{ FlowMaterializer, MaterializerSettings }
import akka.stream.scaladsl.Flow
import java.io.{ FileOutputStream, PrintWriter }
import scala.io.Source
import scala.util.Try

object GroupLogFile {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("Sys")

    val materializer = FlowMaterializer(MaterializerSettings())

    val LoglevelPattern = """.*\[(DEBUG|INFO|WARN|ERROR)\].*""".r

    // read lines from a log file
    val source = Source.fromFile("F:\\IdeaProjects\\game-anlystic\\anlystic\\src\\main\\resources\\logfile.txt", "utf-8")

    //source.getLines().foreach(println)
    Flow(source.getLines()).
      // group them by log level
      groupBy {
        case LoglevelPattern(level) => level
        case other                  => "OTHER"
      }.
      // write lines of each group to a separate file
      foreach {
        case (level, producer) =>
          val output = new PrintWriter(new FileOutputStream(s"target/log-$level.txt"), true)
          println(level)
          Flow(producer).
            foreach(line => {
            output.println(line)
            print(line)
          }).
            // close resource when the group stream is completed
            onComplete(materializer)(_ => Try(output.close()))
      }
//      .
//      onComplete(materializer) { _ =>
//        Try(source.close())
//        system.shutdown()
//      }
  }
}
