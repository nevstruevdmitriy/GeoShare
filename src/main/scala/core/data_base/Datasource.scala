package core.data_base.datasource

import java.sql.Connection
import java.sql.Statement
import org.apache.commons.dbcp2._
import java.net.URI

object Datasource {
    val dbUri = new URI(sys.env.get("DATABASE_URL").getOrElse("postgres://geo_point_local_user:123@localhost:5432/geo_data"))
    val dbUrl = s"jdbc:postgresql://${dbUri.getHost}:${dbUri.getPort}${dbUri.getPath}"
    val connectionPool = new BasicDataSource()

    connectionPool.setDriverClassName("org.postgresql.Driver")
    connectionPool.setUrl(dbUrl)
    connectionPool.setInitialSize(3)

    if (dbUri.getUserInfo != null) {
        connectionPool.setUsername(dbUri.getUserInfo.split(":")(0))
        connectionPool.setPassword(dbUri.getUserInfo.split(":")(1))
    }
}
