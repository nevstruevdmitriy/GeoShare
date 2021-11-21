package core.data_base

import core.data_base.datasource.Datasource
import core.geo.Point
import akka.event.Logging
import scala.collection.mutable.ListBuffer
import java.sql.ResultSet

object DBReader {
    val connection = Datasource.connectionPool.getConnection

    def resultToPoint(result: ResultSet) = Point(result.getString("user_id"), result.getDouble("latitude"), result.getDouble("longitude"))

    def getLastGeoPoint(user_id: String) = {
        val statement = connection.createStatement()        
        
        val result = statement.executeQuery("SELECT user_id, latitude, longitude, time_created FROM points ORDER BY time_created DESC LIMIT 1")

        result.next()
        resultToPoint(result)
    }

    def sendGeoPoint(user_id: String, latitude: Double, longitude: Double) = {
        val statement = connection.createStatement()

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS points (user_id varchar, latitude float, longitude float, time_created timestamp)")
        statement.executeUpdate(s"INSERT INTO points VALUES ('$user_id', $latitude, $longitude, now())") // fix sql injection
    }

    def readGeoPoints() = {
        val statement = connection.createStatement()
        val results = statement.executeQuery("SELECT user_id, latitude, longitude, time_created FROM points")

        var points = ListBuffer.empty[Point]

        while (results.next()) {
            points += resultToPoint(results)
        }

        points.toList
    }
}
