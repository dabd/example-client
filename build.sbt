import play.core.PlayVersion

name := """$name$"""
organization := "$organization$"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.12"

libraryDependencies += filters
libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.1" % Test,
  "com.typesafe.play"      %% "play-ws"            % PlayVersion.current
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "$organization$.com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "$organization$.binders._"
