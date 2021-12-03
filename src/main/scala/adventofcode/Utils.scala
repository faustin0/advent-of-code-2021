package com.ynap.scripts
package adventofcode

import cats.effect.IO

import java.nio.file.Path

object Utils {
  def readLinesFromFile(value: String): fs2.Stream[IO, String] =
    fs2.io.file
      .Files[IO]
      .readAll(Path.of(ClassLoader.getSystemResource(value).toURI), 1024)
      .through(fs2.text.utf8Decode[IO])
      .through(fs2.text.lines[IO])

}
