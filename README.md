# threejsapp


## Compile

Might need to do the following once:

(because of https://stackoverflow.com/questions/64413433/webpack-serve-giving-invalid-regular-expression-error )

nvm use stable

Then:

fastOptJS::webpack


Usage:



Then:

```
http-server -d
```

Open the browser and point it at:

http://localhost:8080/target/scala-2.12/classes/index-dev.html

## Relevant links

- [Bundling without scalajs-bundler](http://appddeevvmeanderings.blogspot.com/2017/11/you-do-not-need-scalajs-bundler-to-use.html)

## scalajs-bundler source


https://github.com/scalacenter/scalajs-bundler/blob/main/sbt-scalajs-bundler/src/main/scala/scalajsbundler/sbtplugin/ScalaJSBundlerPlugin.scala

## scalajs-bundler 


How to rebuild and reload your page on code changes?

scalajs-bundler includes a simple wrapper over webpack-dev-server to simplify your workflow. It is exposed as two stage-level tasks (startWebpackDevServer and stopWebpackDevServer). The standard work session looks like this:

    Spawn background server process:

    > fastOptJS::startWebpackDevServer

    By default the server is started on port 8080. Use webpackDevServerPort setting to change this.
    Instruct SBT to rebuild on source changes:

    > ~fastOptJS

    Now each time you change a source file, Scala.js recompiles it, and webpack-dev-server switches to the updated version.
    Shut down the background process:

    > fastOptJS::stopWebpackDevServer

Additional arguments can be passed to webpack-dev-server via webpackDevServerExtraArgs setting. For example, you can add the following to your build.sbt to make your page reload on every change:

webpackDevServerExtraArgs := Seq("--inline")

Another
---------------------

How to pass extra parameters to webpack

scalajs-bundler invokes webpack with a configuration generated either automatically from the build or set with webpackConfigFile. webpack is then called with the following arguments:

--config <configfile>

You can add extra params to the webpack call, for example, to increase debugging

webpackExtraArgs := Seq("--profile", "--progress", "true")

Note Params are passed verbatim, they are not sanitized and could produce errors when passed to webpack. In particular, don't attempt to override the --config param.
How to use webpack 4

scalajs-bundler (version 0.12.0 onwards) supports webpack 4. To enable webpack 4, set the correct versions in build.sbt

version in webpack := "4.8.1"

version in startWebpackDevServer := "3.1.4"

Additionally, you need to update any webpack plugins your config uses, to Webpack 4 compatible versions.

Webpack 4 has the potential to substantially reduce your webpack compilation times (80% reductions have been observed but your mileage may vary)


Still more
-------------

https://jurosh.com/blog/npm-pass-parameters-into-script

