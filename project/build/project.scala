import sbt._

class project( info: ProjectInfo ) extends DefaultProject( info ) {

  val scalaredis = "net.debasishg" % "redisclient_2.9.0" % "2.3.1"

  val scalatest = "org.scalatest" %% "scalatest" % "1.6.1"

   override def mainClass: Option[String] = Some("org.test.RedisTest")
}