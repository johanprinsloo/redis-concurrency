package org.test

import org.scalatest.FunSuite
import com.redis.RedisClient
import org.test.RedisTest._

/**
 * These tests assumes a locally run redis instance - low tech
 */
class RedisSeqTest extends FunSuite {

  test("redis fed zebrafish genome sequentially") {

    val r = new RedisClient("localhost", 6379)

    r.flushdb
    var idx = 0L

    val strList = parseWordList("./target/scala_2.9.0/resources/GDS3719_full.soft 2")

    val time1 = System.currentTimeMillis()

    strList foreach { x => r.set(idx , x); idx = idx + 1 }

    val time2 = System.currentTimeMillis()
    println("Redis insert time : " + (time2 - time1) + " ms")

    println( r.dbsize.getOrElse("db size err") )
    //println( r.info.getOrElse("no db info") )

    assert( r.disconnect , "disconnected db")

  }

}