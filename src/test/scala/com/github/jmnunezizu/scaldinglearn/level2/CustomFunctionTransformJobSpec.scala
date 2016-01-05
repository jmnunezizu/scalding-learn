package com.github.jmnunezizu.scaldinglearn.level2

import com.github.jmnunezizu.scaldinglearn.JobTestSupport
import com.twitter.scalding.{TypedTsv, TextLine, JobTest, FieldConversions}
import org.specs2.mutable.Specification

class CustomFunctionTransformJobSpec extends Specification with JobTestSupport with FieldConversions {

  "A CustomFunctionTransformJob" should {
    "transform each line into hashCode + text" in {
      val inputFile = Seq(
        1 -> "this is a valid line",
        2 -> "this line contains hadoop"
      )

      var actual = List.empty[(Int, String)]

      JobTest(new CustomFunctionTransformJob(_))
        .arg("input", "inputFile")
        .arg("output", "outputFile")
        .source(TextLine("inputFile"), inputFile)
        .sink[(Int, String)](TypedTsv[(Int, String)]("outputFile")) { outputBuffer =>
          actual = outputBuffer.toList
        }
        .runHadoop
        .finish

      val expected = inputFile.map(t => (t._2.hashCode, t._2))

      actual must beEqualTo(expected)
    }
  }

}
