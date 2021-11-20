package core

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server._
import akka.stream.ActorMaterializer

import core.routes.http.HealthRoute
import core.routes.http.NewPointRoute
import core.routes.http.GetPointsRoute
import core.routes.http.MainRoute

object SimpleHttp extends App {
    implicit val system: ActorSystem = ActorSystem("simple-http")
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    implicit val log = Logging(system, "main")

    val port = sys.env.get("PORT").getOrElse("8080").toInt
    val route: Route = RouteConcatenation.concat(
      HealthRoute.healthRoute, 
      NewPointRoute.newPointRoute,
      GetPointsRoute.getPointsRoute,
      MainRoute.mainRoute)

    val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", port)

    log.info(s"Server started at the port $port")
}
