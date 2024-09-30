ThisBuild / scalaVersion := "3.5.1"

lazy val root = (project in file("."))
  .settings(
    name := "mouse",
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % Versions.slf4j,

      "org.scalatest" %% "scalatest" % Versions.scalatest % Test,
      "org.slf4j" % "slf4j-simple" % Versions.slf4j % Test,
    ),
  )
