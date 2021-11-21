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
                    var is_near = false;
                    val lat = latitude.toDouble
                    val lon = longitude.toDouble
                    try {
                        is_near = DBReader.getLastGeoPoint(user_id).isNear(lat, lon)
                    } finally {}

                    if (!is_near) {
                        DBReader.sendGeoPoint(user_id, lat, lon)
                        complete("true")
                    } else {
                        complete("false")
                    }
            }
        }
    }
}
