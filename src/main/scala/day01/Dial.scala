package day01

import day01.Dial.{MAX_DIAL, NUM_SETTINGS}

class Dial(input: Int) {
  val setting = normalize(input)

  def turn(amount: Int): Dial = Dial(setting + amount)

  override def toString: String = s"Dial($setting)"

  private def normalize(unnormalized: Int): Int =
    if (unnormalized > MAX_DIAL) unnormalized % NUM_SETTINGS
    else if (unnormalized < 0) ((unnormalized % NUM_SETTINGS) + NUM_SETTINGS) % NUM_SETTINGS
    else unnormalized

  override def equals(that: Any): Boolean =
    that match {
      case dial: Dial => dial.setting == this.setting
      case _ => false
    }
}

object Dial {

  val MAX_DIAL: Int = 99
  val NUM_SETTINGS = MAX_DIAL + 1 // Dial starts at 0


  def parseMove(move: String): Int =
    move match {
      case s"R$number" => number.toInt
      case s"L$number" => -number.toInt
    }

  def listDials(dialStart: Dial, moves: List[Int]): Seq[Dial] = {
    moves.foldLeft
      (List(dialStart))
      ((dialList, move) => {
        val newDial = dialList.head.turn(move)
        newDial :: dialList
      }
      )
  }

  def password(lines: List[String]): Int = {
    val dialStart: Dial = Dial(50)
    val moves: List[Int] = lines.map(parseMove)
    val dialPositions = listDials(dialStart, moves)

    dialPositions.count(_.setting == 0)
  }

}