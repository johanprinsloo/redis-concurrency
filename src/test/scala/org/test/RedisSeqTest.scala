package org.test

import org.scalatest.FunSuite
import scala.io.Source._
import com.redis.RedisClient


/**
 * These tests assumes a locally run redis instance - low tech
 */
class RedisSeqTest extends FunSuite {

  test("redis fed shakespeare sequentially") {

    val r = new RedisClient("localhost", 6379)

    val lines = fromFile("./target/scala_2.9.0/resources/shortfolio.txt", "utf-8").getLines
    r.flushdb
    var idx = 0L
    for( line <- lines ){
      idx = idx + 1
      println( line )
      r.set( idx.toString(), line.mkString )
    }

    println( r.info.getOrElse("no db info") )


  }

}