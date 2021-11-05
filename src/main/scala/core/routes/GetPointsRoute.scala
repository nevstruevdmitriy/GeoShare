package core.routes.http

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import core.data_base.DBReader

object GetPointsRoute {
    val getPointsRoute: Route =
        path("get_point") {
            get {
                complete(StatusCodes.OK, DBReader.readGeoPoints())
            }
        }
}
