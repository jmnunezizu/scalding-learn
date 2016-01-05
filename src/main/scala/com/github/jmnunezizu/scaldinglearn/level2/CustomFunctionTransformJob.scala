package com.github.jmnunezizu.scaldinglearn.level2

import com.twitter.scalding.typed.TypedPipe
import com.twitter.scalding.{TypedTsv, TextLine, Args, Job}

class CustomFunctionTransformJob(args: Args) extends Job(args) {

  val inputPath = args("input")
  val outputPath = args("output")

  val input: TypedPipe[String] = TypedPipe.from(TextLine(inputPath))

  input
    .debug
    .map(transformLine(_))
    .write(TypedTsv[(Int, String)](outputPath))

  private def transformLine(line: String): (Int, String) = {
    (line.hashCode, line)
  }

}
