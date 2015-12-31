package com.github.jmnunezizu.scaldinglearn

import scala.io.Source

trait JobTestSupport {

  def getSource(file: String): List[String] = {
    Source.fromFile(file).getLines().toList
  }

}
