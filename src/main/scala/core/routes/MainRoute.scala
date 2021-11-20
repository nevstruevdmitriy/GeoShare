package core.routes.http

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives._
import akka.http.scaladsl.server.RouteConcatenation
import core.data_base.DBReader

object MainRoute {
    val mainRoute: Route = 
        RouteConcatenation.concat(
            path("") { 
                getFromFile("resources/index.html")
            },
            path("js") { 
                getFromFile("resources/js/main.js")
            }
        )
}
