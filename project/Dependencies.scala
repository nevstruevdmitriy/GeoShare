import sbt._

object Dependencies {
  lazy val akkaVersion = "2.5.23"
  lazy val akkaHttpVersion = "10.1.8"
  lazy val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  lazy val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  lazy val akkaTestKit = "com.typesafe.akka" %% "akka-testkit" %  akkaVersion
  lazy val akkaHttpTestKit = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion
  
  lazy val specs2Core = "org.specs2" %% "specs2-core" % "4.5.1"
  
  lazy val postgresql = "org.postgresql" % "postgresql" % "9.3-1102-jdbc41"
  lazy val apache = "org.apache.commons" % "commons-dbcp2" % "2.0.1"

  lazy val qos_logback = "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime
}
