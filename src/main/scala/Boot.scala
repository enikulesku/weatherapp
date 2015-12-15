import akka.actor.{Props, ActorSystem}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import spray.can.Http

import scala.concurrent.duration._

/**
 * This object extends App and is used to 'start' our server
 */
object Boot extends App {

  implicit val system = ActorSystem("server")

  val service = system.actorOf(Props[TestService], "scala-test")

  implicit val timeout = Timeout(10.seconds)

  val interface = "localhost"//Option(args(0)).getOrElse("0.0.0.0")
  val port = 8080//Option(args(1)).getOrElse("8080").toInt

  IO(Http) ? Http.Bind(service, interface, port)
}
