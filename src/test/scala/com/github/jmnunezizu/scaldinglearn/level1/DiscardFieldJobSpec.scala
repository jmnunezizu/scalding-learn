package com.github.jmnunezizu.scaldinglearn.level1

import com.github.jmnunezizu.scaldinglearn.JobTestSupport
import com.twitter.scalding._
import org.specs2.mutable.Specification

import scala.io.Source

class DiscardFieldJobSpec extends Specification with JobTestSupport with FieldConversions {

//  val inputFile = List(
//    ("1", "a"),
//    ("2", "b"),
//    ("3", "c")
//  )

  "A DiscardField Job" should {
    "discard the field 'discardme'" in {
      var actual = List.empty[String]
      val expected = getSource("src/test/resources/level1/expectation.txt")
      val inputFile = Source.fromFile("src/test/resources/level1/source.txt").getLines.toList
      inputFile.foreach(println)

      JobTest(new DiscardFieldJob(_))
        .arg("input", "inputFile")
        .arg("output", "outputFile")
        .source(TypedTsv[(String, String)]("inputFile"), inputFile)
        .sink[String](TypedTsv[String]("outputFile")) { outputBuffer =>
          actual = outputBuffer.toList
        }
        .runHadoop
        .finish

      actual must beEqualTo(List("a", "b", "c"))
    }
  }

}
