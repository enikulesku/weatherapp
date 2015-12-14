import akka.actor.{ActorRefFactory, Actor}
import org.json4s.{DefaultFormats, Formats}
import org.slf4j.LoggerFactory
import spray.httpx.Json4sJacksonSupport
import spray.routing.HttpService

import scala.concurrent.ExecutionContext

class TestService extends Actor with Routes {
  def actorRefFactory: ActorRefFactory = context

  def receive: Receive = runRoute(baseRoute)
}

trait Routes extends HttpService with Json4sJacksonSupport {
  val logger = LoggerFactory.getLogger(classOf[Routes])

  implicit def json4sJacksonFormats: Formats = DefaultFormats

  implicit def dispatcher: ExecutionContext = actorRefFactory.dispatcher

  val baseRoute =
    path("alive") {
      get {
        parameter('id ? 1) { (shallow) => ctx =>
          ctx.complete("ok")
        }
      }
    }
}
