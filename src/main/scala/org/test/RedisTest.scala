package org.test

import com.redis._
import io.Source

object RedisTest extends App {
  val r = new RedisClient("localhost", 6379)

  def getparse( key: String ) : Unit = {
    r.get( key ) match {
    case None => println("None")
    case Some( x: String) => println( "Sting Value: " + x )
    //case Some( y: Int ) => println( " Int Value: " + y )
    case Some( any ) => println(" Any Value: " + any )
    }
  }

  println(">>> redis concurrency test with " + r.toString )
  val setb = r.set("b", "_BB BB BB_")
  println(">>> key105 SET : " + r.set("key105", "value105") )

  val key105 = r.get("a")
  println(">>> key105 GET : " +  key105.getOrElse("lookup for key 105 failed") )

  getparse("key105")
  getparse("a")
  getparse("b")
  getparse("c")
  getparse("d")

  r disconnect

    /**
   * Reads a word list text file
   * Returns a ragged list of char lists
   */
  def parseWordList(filename: String): List[String] = {

    var buffer: List[String] = List.empty
    Source.fromFile(filename, "utf-8").getLines
      .foreach {
      line => buffer = List(line) ::: buffer
    }
    return buffer
  }
}