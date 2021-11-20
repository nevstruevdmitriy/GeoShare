package core.geo

import java.sql.ResultSet
import java.sql.Timestamp

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._


final case class Point(userId: String, latitude: Double, longitude: Double)

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
    implicit val pointFormat = jsonFormat3(Point)
}
