name := """ebeancrud"""
organization := "siddharth"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

libraryDependencies += guice
libraryDependencies += "org.postgresql" % "postgresql" % "9.3-1100-jdbc4"

lazy val myProject = (project in file("."))
  .enablePlugins(PlayJava, PlayEbean)