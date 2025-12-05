package day01

import day01.Dial._

class Dial(input: Int) {
  val setting = Dial.normalize(input)

  def turn(amount: Int): Dial = Dial(setting + amount)

  // Count of zeros passed getting TO current setting by the given turn
  // (current setting is where the dial ends up after the given turn)
  def zerosPassed(turn: Int): Int = {
    // A full rotation will always pass (or end on) exactly 1 zero
    val maybeOneMore = if (partialTurnPassedZero(turn)) 1 else 0
    fullTurns(turn) + maybeOneMore
  }

  // The final, partial rotation
  // passed (or ended on) zero if the turn ending on the current setting
  // was greater than how far (in the direction of the turn)
  // the current setting is beyond zero
  def partialTurnPassedZero(turn: Int): Boolean = {
    if (partial(turn) == 0) false
    else if (setting == 0) true
    else if (turn > 0) partial(turn) > setting
    else Math.abs(partial(turn)) > NUM_SETTINGS - setting
  }

  override def toString: String = s"Dial($setting)"

  override def equals(that: Any): Boolean =
    that match {
      case dial: Dial => dial.setting == this.setting
      case _ => false
    }
}

object Dial {

  val MAX_DIAL: Int = 99
  val NUM_SETTINGS = MAX_DIAL + 1 // Dial starts at 0

  def normalize(unnormalized: Int): Int =
    if (unnormalized > MAX_DIAL) unnormalized % NUM_SETTINGS
    else if (unnormalized < 0) ((unnormalized % NUM_SETTINGS) + NUM_SETTINGS) % NUM_SETTINGS
    else unnormalized

  def parseTurn(turn: String): Int =
    turn match {
      case s"R$number" => number.toInt
      case s"L$number" => -number.toInt
    }

  def listDials(dialStart: Dial, turns: List[Int]): Seq[Dial] = {
    turns.foldLeft
      (List(dialStart))
      ((dialList, turn) => {
        val newDial = dialList.head.turn(turn)
        newDial :: dialList
      })
  }

  def password(lines: List[String]): Int = {
    val dialStart: Dial = Dial(50)
    val turns: List[Int] = lines.map(parseTurn)
    val dialPositions = listDials(dialStart, turns)

    dialPositions.count(_.setting == 0)
  }

  def listDialsAndTurns(dialStart: Dial, turns: List[Int]): Seq[(Dial, Int)] = {
    turns.foldLeft
      (List((dialStart, 0)))
      ((dialList, turn) => {
        val newDial = dialList.head._1.turn(turn)
        (newDial, turn) :: dialList
      })
  }

  def fullTurns(turn: Int) = Math.abs(turn) / NUM_SETTINGS

  def direction(turn: Int) = if (turn >= 0) +1 else -1

  def partial(turn: Int) = turn - (fullTurns(turn) * NUM_SETTINGS * direction(turn))

  def passwordv2(lines: List[String]): Int = {
    val dialStart: Dial = Dial(50)
    val turns: List[Int] = lines.map(parseTurn)
    val dialsAndTurns = listDialsAndTurns(dialStart, turns)

    dialsAndTurns.map((d, t) => d.zerosPassed(t)).sum
  }
}