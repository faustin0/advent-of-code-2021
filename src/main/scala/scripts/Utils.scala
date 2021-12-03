package com.faustin0.aoc
package scripts

import cats.effect.IO
import fs2.io.file.Path

import java.nio.file

object Utils {

  def readLinesFromFile(value: String): fs2.Stream[IO, String] =
    fs2.io.file
      .Files[IO]
      .readAll(Path.fromNioPath(file.Path.of(ClassLoader.getSystemResource(value).toURI)))
      .through(fs2.text.utf8.decode[IO])
      .through(fs2.text.lines[IO])

}
