//import java.net.InetSocketAddress
//
//import akka.actor.{ Actor, ActorRef, ActorSystem, Props }
//import com.typesafe.config.ConfigFactory
//import net.sigusr.mqtt.api._
//
//import scala.concurrent.duration.{ FiniteDuration, _ }
//import scala.util.Random

object LocalPublisher {

}

//class LocalPublisher extends Actor {
//
//  import context.dispatcher
//
//  val localPublisher: String = "publisher"
//
//  context.actorOf(Manager.props(new InetSocketAddress("m20.cloudmqtt.com", 11254))) ! Connect(localPublisher, user = Option("user"), password = Option("pass"))
//
//  def scheduleRandomMessage(): Unit = {
//    val message = toPublish(Random.nextInt(length))
//    context.system.scheduler.scheduleOnce(FiniteDuration(Random.nextInt(2000).toLong + 1000, MILLISECONDS), self, Random.nextBoolean() match {
//      case true => "on"
//      case true => "off"
//    })
//    ()
//  }
//
//  def receive: Receive = {
//    case Connected ⇒
//      println("Successfully connected to localhost:1883")
//      println(s"Ready to publish to topic [ $localPublisher ]")
//      scheduleRandomMessage()
//      context become ready(sender())
//    case ConnectionFailure(reason) ⇒
//      println(s"Connection to localhost:1883 failed [$reason]")
//  }
//
//  def ready(mqttManager: ActorRef): Receive = {
//    case m: String ⇒
//      println(s"Publishing [ $m ]")
//      mqttManager ! Publish(localPublisher, m.getBytes("UTF-8").to[Vector])
//      scheduleRandomMessage()
//  }
//}
