package com.github.jmnunezizu.scaldinglearn.level1

import com.twitter.scalding.typed.TypedPipe
import com.twitter.scalding.{TypedTsv, Job, Args}

case class TupleWithNoise(noise: String, value: String) {
  def toTuple: (String, String) = (noise, value)
}

object TupleWithNoise {
  def fromTuple(tuple: (String, String)) = TupleWithNoise(tuple._1, tuple._2)
}

class DiscardFieldJob(args: Args) extends Job(args) {

  val inputPath = args("input")
  val outputPath = args("output")

  val rawInput: TypedPipe[(String, String)] = TypedPipe.from(TypedTsv[(String, String)](inputPath))

  rawInput
    .map(TupleWithNoise.fromTuple(_).value) // just get the value
    .write(TypedTsv[String](outputPath))

}
