package cn.mob.anlystic.sample


/**
 * @version 1.0 date : 2014/9/1
 * @author : Dempe
 */
object Sample extends App {

  def withClose(closeAble: {def close(): Unit},
                op: {def close(): Unit} => Unit) {
    try {
      op(closeAble)
    } finally {
      closeAble.close()
    }
  }

  class Connection {
    def close() = println("close Connection")
  }

  val conn: Connection = new Connection()
  withClose(conn, conn =>
    println("do something with Connection"))


}
