package core.routes.http

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import core.data_base.DBReader
import play.api.libs.json._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._
import akka.http.scaladsl.server.Directives

import core.geo._

object GetPointsRoute extends Directives with JsonSupport {
    val getPointsRoute: Route =
        path("get_point") {
            get {
                complete(DBReader.readGeoPoints())
            }
        }
}
