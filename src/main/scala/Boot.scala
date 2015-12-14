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

  val interface = Option(args(0)).getOrElse("localhost")
  val port = Option(args(1)).getOrElse("8080").toInt

  IO(Http) ? Http.Bind(service, interface, port)
}
