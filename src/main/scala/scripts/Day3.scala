package com.faustin0.aoc
package scripts

import cats.effect.IO
import cats.effect.IOApp.Simple
import cats.implicits._
import fs2._

object Day3 extends Simple {

  case class Gamma(value: Char)
  case class Epsilon(value: Char)

  def extractMeasurement(column: List[Char]): (Gamma, Epsilon) = {
    val occurrences = column.groupMapReduce(identity)(c => 1)(_ + _)
    val (gbit, _) = occurrences.maxBy { case (bit, count) => count }
    val (ebit, _) = occurrences.minBy { case (bit, count) => count }
    (Gamma(gbit), Epsilon(ebit))
  }

  def processRow(): Pipe[IO, List[Char], Any] =
    rows => rows.map(el => Stream.emits(el).map(c => Stream.emit(c)))

  def run: IO[Unit] =
    Utils
      .readLinesFromFile("day3.txt")
      .map(s => s.toList.zipWithIndex)
      .compile
      .toList
      .map { table =>
        val value: List[Map[Int, List[Char]]] = for {
          row <- table
          cln  = row.groupMap { case (c, idx) => idx } { case (c, idx) => c }
        } yield cln
        value.fold(Map.empty) { case (value, value1) => value1.combine(value) }
      }
      .map(_.values.map(extractMeasurement))
      .flatMap(l => IO(println(l)))
}
