package core.geo

import java.sql.ResultSet
import java.sql.Timestamp

case class Point(results: ResultSet) {
    val userId: String = results.getString("user_id")
    val latitude: Double = results.getDouble("latitude")
    val longitude: Double = results.getDouble("longitude")
    val timeCreated: Timestamp = results.getTimestamp("time_created")

    override def toString(): String = {
        s"id: $userId, ($latitude, $longitude), created: $timeCreated\n"
    }
}
