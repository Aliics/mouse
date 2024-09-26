ThisBuild / scalaVersion := "3.5.1"

lazy val root = (project in file("."))
  .settings(
    name := "mouse",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % Versions.scalatest % Test,
    ),
  )
