package com.ynap.scripts
package adventofcode

import cats.effect.IO
import cats.effect.IOApp.Simple

object Day1 extends Simple {

//1393
  def run: IO[Unit] =
    Utils.readLinesFromFile("day1.txt")
      .map(s => s.toInt)
      .zipWithPrevious
      .collect { case (Some(prev), curr) if curr > prev => curr }
      .compile
      .count
      .flatMap(l => IO(println(l)))
}
