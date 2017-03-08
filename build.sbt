import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import ScalateKeys._

val ScalatraVersion = "2.4.0"

ScalatraPlugin.scalatraSettings

scalateSettings

organization := "tendcloud.tianye"

name := "UserAthuc"

version := "0.1.0"

scalaVersion := "2.11.8"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
  "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.5" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.2.15.v20160210" % "container",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.mybatis.scala" % "mybatis-scala-core_2.10" % "1.0.2",
  "org.mybatis.scala" % "mybatis-scala-parent" % "1.0.2",
  "org.apache.shiro" % "shiro-core" % "1.3.2",
  "org.apache.shiro" % "shiro-web" % "1.3.2",
  "commons-logging" % "commons-logging" % "1.1.3",
  "mysql" % "mysql-connector-java" % "5.1.40",
  "org.apache.shiro" % "shiro-ehcache" % "1.4.0-RC2"
)

scalateTemplateConfig in Compile := {
  val base = (sourceDirectory in Compile).value
  Seq(
    TemplateConfig(
      base / "webapp" / "WEB-INF" / "templates",
      Seq.empty,  /* default imports should be added here */
      Seq(
        Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
      ),  /* add extra bindings here */
      Some("templates")
    )
  )
}

enablePlugins(JettyPlugin)
