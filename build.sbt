version := "0.7"

organization := "org.tpolecat"

name := "argali"

scalaVersion := "2.12.1"

crossScalaVersions := List("2.10.6", "2.11.8", "2.12.1")

scalacOptions ++= List(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-Xlint",
  "-language:existentials",
  "-language:higherKinds",
  "-Ypartial-unification"
)

libraryDependencies ++= List(
  "org.typelevel" %% "cats" % "0.9.0",
  "org.scalacheck" %% "scalacheck" % "1.12.6" % "test")

addCompilerPlugin("org.spire-math" % "kind-projector" % "0.9.3" cross CrossVersion.binary)
