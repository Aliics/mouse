ThisBuild / scalaVersion := "3.5.1"

lazy val root = (project in file("."))
  .settings(
    name := "examples",
    libraryDependencies ++= Seq(
      "io.github.aliics" %% "mouse" % "0.5.1",
      "org.slf4j" % "slf4j-simple" % "2.0.13",
    ),
  )
