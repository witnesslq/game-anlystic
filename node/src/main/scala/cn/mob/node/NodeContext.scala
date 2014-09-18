package cn.mob.node


import akka.actor._
import com.typesafe.config.ConfigFactory
import scala.concurrent.duration._
/**
 * @version 1.0 date : 2014/9/18
 * @author : Dempe 
 */
class NodeContext(nodeConf:NodeConf) {

  def this()= this(new NodeConf())
  def this(localNode:String,preNodes:String,nodeConf:NodeConf)=
      this(NodeContext.updateConf(localNode,preNodes,nodeConf))


  def start()={
    val conf = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + nodeConf.getLocalNode.getPort).
      withFallback(ConfigFactory.load("common"))

    val system = ActorSystem(nodeConf.getLocalNode.getNodename, conf)



    //启动心跳和job actor
    system.actorOf(Props[HeartbeatActor], Constants.IDENTIFY)
    system.actorOf(Props[JobActor], Constants.JOB)

    //添加死信的监听
    // addDeadLetterListener


    val preNodes: java.util.List[Node] = nodeConf.getPreNodes()
    val preNodeActors = preNodes.toArray.map(node => {
      val path = node.toString + "/" + Constants.USER + "/" + Constants.IDENTIFY
      println("===>" + path)
      system.actorSelection(ActorPath.fromString(path))
    })

    val localNode = nodeConf.getLocalNode

    import system.dispatcher

    //每隔60s向master发送心跳
    system.scheduler.schedule(0 milliseconds, 60000 milliseconds) {
      //通知上个节点 本节点可用
      preNodeActors.foreach(actor => actor ! localNode)
    }

    def addDeadLetterListener = {
      val listener = system.actorOf(Props(new Actor {
        def receive = {
          case d: DeadLetter => {
            //println("sender===>"+d.sender.path.address)
            println("This is a dead letter====>"+d)
          }
        }
      }))
      system.eventStream.subscribe(listener, classOf[DeadLetter])
    }

  }



}
object NodeContext{
  private def updateConf(localNode:String,preNodes:String,nodeConf:NodeConf):NodeConf={
    nodeConf.setLocalNode(nodeConf.fromString(localNode))
    val preNodeList : java.util.List[Node] = new java.util.ArrayList[Node]()
    preNodes.split(",").foreach(nodeStr=>preNodeList.add(nodeConf.fromString(nodeStr)))
    nodeConf.setPreNodes(preNodeList)
    nodeConf
  }
}
