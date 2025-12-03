
import day01.Dial
import day01.Dial._

import scala.io.Source

val testResource = Source.fromResource("test01.txt")
val testLines: List[String] = testResource.getLines.toList

val resource = Source.fromResource("input01.txt")
val lines: List[String] = resource.getLines.toList



password(testLines)
password(lines)

val easyLines = List("L0", "R0", "L5", "R5")
val easyMoves = easyLines.map(parseMove)
listDials(Dial(50), easyMoves)

val easyLines2 = List("L50", "R10")
easyLines2.map(parseMove)

// Part 2

def passedZero(dialAndMove: (Dial, Int)): Int = {
  val d: Int = dialAndMove._1.setting
  val m: Int = dialAndMove._2
  d == 0 || (m > 0 && m > d) || (m < 0 && -m > MAX_DIAL - d)

  if (m > 0 && m > d) 1 + (m - 1) / NUM_SETTINGS
  else if (m < 0 && -m > MAX_DIAL - d) 1 + (-m - 1) / NUM_SETTINGS
  else 0
}

def listDialsAndMoves(dialStart: Dial, moves: List[Int]): Seq[(Dial, Int)] = {
  moves.foldLeft
    (List((dialStart, 0)))
    ((dialList, move) => {
      val newDial = dialList.head._1.turn(move)
      (newDial, move) :: dialList
    }
    )
}

def passwordv2(lines: List[String]): Int = {
  val dialStart: Dial = Dial(50)
  val moves: List[Int] = lines.map(parseMove)
  val dialsAndMoves = listDialsAndMoves(dialStart, moves)

//  println(dialsAndMoves)
//  println(dialsAndMoves.map(passedZero))

  dialsAndMoves.map(passedZero).sum
}

listDialsAndMoves(Dial(50), List(1000))
listDialsAndMoves(Dial(50), List(1000)).map(passedZero)
listDialsAndMoves(Dial(50), List(-1000)).map(passedZero)
listDialsAndMoves(Dial(0), List(1000)).map(passedZero)
listDialsAndMoves(Dial(0), List(-1000)).map(passedZero)
listDialsAndMoves(Dial(0), List(1100)).map(passedZero)
listDialsAndMoves(Dial(0), List(-1100)).map(passedZero)
listDialsAndMoves(Dial(0), List(1099)).map(passedZero)
listDialsAndMoves(Dial(0), List(-1099)).map(passedZero)
passedZero(Dial(99), -3)
passedZero(Dial(99), -2)
passedZero(Dial(99), -1)
passedZero(Dial(1), 3)
passedZero(Dial(1), 2)
passedZero(Dial(1), 1)
passedZero(Dial(1), 103)
passedZero(Dial(1), 102)
passedZero(Dial(1), 101)

passwordv2(testLines)
passwordv2(lines)
