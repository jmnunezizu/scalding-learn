package com.github.jmnunezizu.scaldinglearn.level2

import com.twitter.scalding.typed.TypedPipe
import com.twitter.scalding.{TypedTsv, TextLine, Job, Args}

class FilterJob(args: Args) extends Job(args) {

  val inputPath = args("input")
  val outputPath = args("output")

  val rawInput: TypedPipe[String] = TypedPipe.from(TextLine(inputPath))

  rawInput
    .filterNot(_.toLowerCase.contains("hadoop"))
    .write(TypedTsv[String](outputPath))

}
