import Dependencies._

ThisBuild / scalaVersion     := "2.11.11"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file(".")).enablePlugins(JavaAppPackaging)
  .settings(
    name := "Geo share",
    libraryDependencies ++= Seq(
      qos_logback,
      postgresql,
      apache,
      jdbc,
      akkaHttp,
      akkaStream,
      akkaTestKit % Test,
      akkaHttpTestKit % Test,
      specs2Core % Test
    )
  )
