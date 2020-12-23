
name := "FinalTask"

version := "0.1"

scalaVersion := "2.13.1"

val circeVersion = "0.12.0"

libraryDependencies ++= Seq(
  "org.scalaj" %% "scalaj-http" % "2.4.2",
  "io.circe"  %% "circe-core"     % circeVersion,
  "io.circe"  %% "circe-generic"  % circeVersion,
  "io.circe"  %% "circe-parser"   % circeVersion,
  "mysql" % "mysql-connector-java" % "5.1.24"
)