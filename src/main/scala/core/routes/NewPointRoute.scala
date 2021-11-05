package core.routes.http

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import core.data_base.DBReader

object NewPointRoute {
    val newPointRoute: Route =
        path("new_point") {
            post {
            parameters("userId", "latitude", "longitude") {
                (user_id, latitude, longitude) =>
                    DBReader.sendGeoPoint(user_id, latitude.toDouble, longitude.toDouble);
                    complete(StatusCodes.OK)
            }
        }
    }
}
