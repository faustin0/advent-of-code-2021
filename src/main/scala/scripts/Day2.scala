package com.faustin0.aoc
package scripts

import cats.effect.IO
import cats.effect.IOApp.Simple

object Day2 extends Simple {

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
      .fold((0, 0)) { case ((pos, depth), curr) =>
        curr match {
          case Horizontal(x) => (pos + x, depth)
          case Depth(x)      => (pos, depth + x)
        }
      }
      .flatMap { case (x, y) => IO(println(x * y)) }
}
