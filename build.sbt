name := "weather-app"

version := "1.0"

jarName in assembly := "spray-template-fat.jar"

scalaVersion := "2.11.7"

val sprayVersion = "1.3.3"
val akkaVersion = "2.4.0"

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies += "io.spray" %% "spray-can" % sprayVersion

libraryDependencies += "io.spray" %% "spray-routing" % sprayVersion

libraryDependencies += "io.spray" %% "spray-client" % sprayVersion

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % akkaVersion

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.9"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.13"

libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % akkaVersion

libraryDependencies += "io.spray" %% "spray-json" % "1.3.2"

libraryDependencies += "org.json4s" % "json4s-jackson_2.11" % "3.3.0"

//libraryDependencies += "net.sigusr" %% "scala-mqtt-client" % "0.6.0"

enablePlugins(DockerPlugin)
// Make docker depend on the assembly task, which generates a fat jar file
docker <<= (docker dependsOn assembly)

dockerfile in docker := {
  val jarFile = (outputPath in assembly).value
  val appDirPath = "/app"
  val jarTargetPath = s"$appDirPath/${jarFile.name}"

  new Dockerfile {
    from("java")
    add(jarFile, jarTargetPath)
    workDir(appDirPath)
    entryPoint("java", "-jar", jarTargetPath)
    expose(8080)
  }
}

buildOptions in docker := BuildOptions(cache = false)