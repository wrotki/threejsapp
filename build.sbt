lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(ScalaJSBundlerPlugin)
  .settings(
    inThisBuild(List(
      organization := "com.otherbrane",
      version      := "0.1-SNAPSHOT",
      scalaVersion := "2.12.6"
    )),
    name := "threejsapp",
    libraryDependencies ++= Seq(
      "org.scala-js"  %%% "scalajs-dom"    % "1.1.0",
      "com.otherbrane" %%% "threejsfacade" % "0.1-SNAPSHOT" ,
      "com.otherbrane" %%% "threejswidgets" % "0.1-SNAPSHOT" ,
      "org.scalatest" %%% "scalatest"      % "3.2.7"    % "test"
    ),
    Compile/npmDependencies ++= Seq(
      "three" -> "0.137.5"
    ),
    scalaJSUseMainModuleInitializer := true
  )


// Automatically generate index-dev.html which uses *-fastopt.js
  Compile/resourceGenerators += Def.task {
  val source = (Compile/resourceDirectory).value / "index.html"
  val target = (Compile/resourceManaged).value / "index-dev.html"

  val fullFileName = (Compile/fullOptJS/artifactPath).value.getName
  val fastFileName = (Compile/fastOptJS/artifactPath).value.getName

  IO.writeLines(target,
    IO.readLines(source).map {
      line => line.replace(fullFileName, fastFileName)
    }
  )

  Seq(target)
}.taskValue
