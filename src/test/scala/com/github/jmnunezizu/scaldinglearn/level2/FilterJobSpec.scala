package com.github.jmnunezizu.scaldinglearn.level2

import com.github.jmnunezizu.scaldinglearn.JobTestSupport
import com.twitter.scalding.{TypedTsv, TextLine, JobTest, FieldConversions}
import org.specs2.mutable.Specification

class FilterJobSpec extends Specification with JobTestSupport with FieldConversions {

  "A FilterJob" should {
    "discard lines that contain the word hadoop" in {
      val inputFile = Seq(
        1 -> "this is a valid line",
        2 -> "this line contains hadoop"
      )

      var actual = List.empty[String]

      JobTest(new FilterJob(_))
        .arg("input", "inputFile")
        .arg("output", "outputFile")
        .source(TextLine("inputFile"), inputFile)
        .sink[String](TypedTsv[String]("outputFile")) { outputBuffer =>
          actual = outputBuffer.toList
        }
        .runHadoop
        .finish

      actual must beEqualTo(inputFile.head._2 :: Nil)
    }
  }

}
