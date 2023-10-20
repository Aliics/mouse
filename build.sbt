ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "mouse",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.15" % "test",
    ),
  )
