package com.ynap.scripts
package adventofcode

import cats.effect.IO
import cats.effect.IOApp.Simple

object Day2_2 extends Simple {

  sealed trait Movement
  case class Horizontal(pos: Int) extends Movement
  case class Depth(pos: Int)      extends Movement

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
      .fold((0, 0, 1)) { case ((pos, depth, aim), curr) =>
        curr match {
          case Horizontal(x) => (pos + x, depth + (pos * aim), aim + x)
          case Depth(x)      => (pos, depth + pos, aim + x)
        }
      }
      .flatMap { case (x, y, a) => IO(println(x * y)) }
}
