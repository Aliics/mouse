import sbt.url
ThisBuild / version := "0.5.2"
ThisBuild / versionScheme := Some("early-semver")
ThisBuild / organization := "io.github.aliics"
ThisBuild / organizationName := "aliics"
ThisBuild / organizationHomepage := Some(url("https://github.com/aliics"))
ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/aliics/mouse"),
    "https://github.com/aliics/mouse.git",
  ),
)

ThisBuild / homepage := Some(url("https://github.com/aliics/mouse"))
ThisBuild / developers := List(
  Developer(
    id = "Aliics",
    name = "Alexander Johnston",
    email = "Aliics@hotmail.com",
    url = url("https://github.com/aliics"),
  ),
)
ThisBuild / description := "Small, simple, minimal HTTP server library written in Scala."
ThisBuild / licenses := List(
  "MIT" -> new URL("https://www.opensource.org/licenses/mit-license.php"),
)
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishMavenStyle := true
ThisBuild / publishTo := {
  val nexus = "https://s01.oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
