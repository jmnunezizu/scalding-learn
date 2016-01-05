package com.github.jmnunezizu.scaldinglearn.level2

import com.github.jmnunezizu.scaldinglearn.JobTestSupport
import com.twitter.scalding.{TypedTsv, JobTest, TextLine, FieldConversions}
import org.specs2.mutable.Specification

class TransformJobSpec extends Specification with JobTestSupport with FieldConversions {

  "TransformJob" should {
    "convert everything to uppercase" in {
      val inputFile = Seq(
        1 -> "this is a valid line",
        2 -> "this line contains hadoop"
      )

      var actual = Seq.empty[String]

      JobTest(new TransformJob(_))
        .arg("input", "inputFile")
        .arg("output", "outputFile")
        .source(TextLine("inputFile"), inputFile)
        .sink[String](TypedTsv[String]("outputFile")) { outputBuffer =>
          actual = outputBuffer.toList
        }
        .runHadoop
        .finish

      actual must beEqualTo(inputFile.map(_._2.toUpperCase))
    }
  }

}
