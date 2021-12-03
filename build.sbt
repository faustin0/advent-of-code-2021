name := "scala-scripts"

version := "0.1"

scalaVersion := "2.13.6"

idePackagePrefix := Some("com.ynap.scripts")

libraryDependencies ++= Seq(
  "org.typelevel"          %% "cats-effect" % "3.2.8",
  "co.fs2" %% "fs2-io" % "3.1.3"
)
