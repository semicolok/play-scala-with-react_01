name := """test-board-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
lazy val skinnyVersion = "2.1.+"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  jdbc,
  cache,
//  ws,
  "org.skinny-framework" %% "skinny-orm" % skinnyVersion, // instead of anorm
  "org.skinny-framework" %% "skinny-http-client" % skinnyVersion, // instead of ws
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.5.+",
  "org.flywaydb" %% "flyway-play" % "3.0.+",
  "mysql" % "mysql-connector-java" % "5.1.39",

  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
