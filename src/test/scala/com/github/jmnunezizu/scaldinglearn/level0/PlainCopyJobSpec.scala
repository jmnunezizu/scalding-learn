package com.github.jmnunezizu.scaldinglearn.level0

import com.github.jmnunezizu.scaldinglearn.JobTestSupport
import com.twitter.scalding._
import org.specs2.mutable.Specification

import scala.io.Source

class PlainCopyJobSpec extends Specification with JobTestSupport {

  "A PlainCopy job" should {
    "copy file to target directory" >> {
      val inputFile = getSource("src/test/resources/hadoop-wiki-sample.txt")
      var actual = List.empty[String]
      val expected = getSource("src/test/resources/level0/expectation.txt")

      JobTest(new PlainCopyJob(_))
        .arg("input", "inputFile")
        .arg("output", "outputFile")
        .source(TextLine("inputFile"), inputFile)
        .sink[String](TextLine("outputFile")) { outputBuffer =>
          actual = outputBuffer.toList
        }
        .run
        .finish

      actual must beEqualTo(expected)
    }
  }

}
