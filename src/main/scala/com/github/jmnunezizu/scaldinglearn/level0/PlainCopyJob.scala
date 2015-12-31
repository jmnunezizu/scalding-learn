package com.github.jmnunezizu.scaldinglearn.level0

import com.twitter.scalding._

/**
 * Level0 is a very basic example of how to do a copy. It should provide you
 * with enough leads about where to look for more information. You will need to
 * complete later levels in order to make the tests pass.
 *
 * It has to copy "input" to "output".
 */
class PlainCopyJob(args: Args) extends Job(args) {

  val input = TextLine(args("input"))
  val output = TextLine(args("output"))

  input.read.write(output)

}
