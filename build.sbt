name in ThisBuild := "free-rdf"

organization in ThisBuild := "org.bertails"

version in ThisBuild := "0.1-SNAPSHOT"

scalaVersion in ThisBuild := "2.11.6"

scalacOptions in ThisBuild ++= Seq("-feature", "-deprecation", "-unchecked")

lazy val root = project.in(file("."))
