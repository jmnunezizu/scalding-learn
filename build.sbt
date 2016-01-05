organization := "com.github.jmnunezizu"

name := "scalding-learn"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

javacOptions ++= Seq("-source", "1.8")

resolvers ++= Seq(
  "Apache Repo"   at "https://repository.apache.org/content/repositories/releases",
  "Thrift-Repo"   at "http://people.apache.org/~rawson/repo",
  "ClouderaRepo"  at "https://repository.cloudera.com/content/repositories/releases",
  "ClouderaRcs"   at "https://repository.cloudera.com/artifactory/cdh-releases-rcs",
  "Twitter Maven" at "http://maven.twttr.com",
  "MVN Repo"      at "http://mvnrepository.com/artifact",
  "releases"      at "http://scala-tools.org/repo-releases",
  "Conjars"       at "http://conjars.org/repo"
)

libraryDependencies ++= Seq(
  "cascading" % "cascading-core" % "3.0.0",
  "cascading" % "cascading-local" % "3.0.0",
  "cascading" % "cascading-hadoop" % "3.0.0",

  "com.twitter" %% "scalding-core" % "0.15.0" exclude("cascading", "cascading-local") exclude("cascading", "cascading-hadoop") exclude("cascading", "cascading-core"),
  "com.twitter" %% "scalding-args" % "0.15.0" exclude("cascading", "cascading-local") exclude("cascading", "cascading-hadoop") exclude("cascading", "cascading-core"),

  "org.apache.hadoop" % "hadoop-core" % "1.2.1",
  "org.apache.hadoop" % "hadoop-common" % "2.7.1",

  "org.specs2" %% "specs2-core" % "3.6.4" % "test"
)

val settings = Seq(
  fork in run := true,
  fork in Test := true,
  fork in testOnly := true
)

scalacOptions in Test ++= Seq("-Yrangepos")
