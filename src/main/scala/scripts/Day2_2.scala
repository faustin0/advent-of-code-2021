package com.faustin0.aoc
package scripts

import cats.effect.IO
import cats.effect.IOApp.Simple

object Day2_2 extends Simple {

  sealed trait Movement
  case class Horizontal(pos: Long) extends Movement
  case class Depth(pos: Long)      extends Movement

  def run: IO[Unit] =
    Utils
      .readLinesFromFile("day3.txt")
      .map(str => (str.split(" ")(0), str.split(" ")(1).toInt))
      .collect {
        case ("forward", x) => Horizontal(x)
        case ("up", x)      => Depth(-x)
        case ("down", x)    => Depth(x)
      }
      .compile
      .fold((0L, 0L, 0L)) { case ((pos, depth, aim), curr) =>
        curr match {
          case Horizontal(x) => (pos + x, depth + (x * aim), aim)
          case Depth(x)      => (pos, depth, aim + x)
        }
      }
      .flatMap { case (pos, depth, a) => IO(println(pos * depth)) }
}
