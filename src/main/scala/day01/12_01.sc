
import day01.Dial
import day01.Dial._

import scala.io.Source

val testResource = Source.fromResource("test01.txt")
val testLines: List[String] = testResource.getLines.toList

val resource = Source.fromResource("input01.txt")
val lines: List[String] = resource.getLines.toList

Dial.normalize(100)
Dial.normalize(-100)

password(testLines)
password(lines)

val easyLines = List("L0", "R0", "L5", "R5")
val easyTurns = easyLines.map(parseTurn)
listDials(Dial(50), easyTurns)

val easyLines2 = List("L50", "R10")
easyLines2.map(parseTurn)

// Part 2

99 / 100
100 / 100
(0 + 99 + 1) / NUM_SETTINGS
101 / 100
201 / 100d

// Turn ending on 0 passes 1 zero if turn is
// <= 100
Dial(0).zerosPassed(0) // 0
Dial(0).zerosPassed(99) // 1
Dial(0).zerosPassed(100) // 1
Dial(0).zerosPassed(101) // 2
Dial(0).zerosPassed(200) // 2
Dial(0).zerosPassed(201) // 3

Dial(1).zerosPassed(0) // 0
Dial(1).zerosPassed(99) // 1
Dial(1).zerosPassed(100) // 1
Dial(1).zerosPassed(101) // 2
Dial(1).zerosPassed(200) // 2
Dial(1).zerosPassed(201) // 3

// Turn of 100 will always pass (or end on)
// exactly one zero regardless of end
Dial(0).zerosPassed(100) // 1
Dial(1).zerosPassed(100) // 1
Dial(99).zerosPassed(100) // 1

Dial(10).partialTurnPassedZero(10)
Dial(82).partialTurnPassedZero(-30)
NUM_SETTINGS
Dial.normalize(-30)

//Symmetry
Dial(0).zerosPassed(0) // 0
Dial(0).zerosPassed(-99) // 1
Dial(0).zerosPassed(-100) // 1
Dial(0).zerosPassed(-101) // 2
Dial(0).zerosPassed(-200) // 2
Dial(0).zerosPassed(-201) // 3

Dial(1).zerosPassed(0) // 0
Dial(1).zerosPassed(-99) // 0
Dial(1).zerosPassed(-100) // 1
Dial(1).zerosPassed(-101) // 2
Dial(1).zerosPassed(-200) // 2
Dial(1).zerosPassed(-201) // 3

Dial(1).partialTurnPassedZero(0)
Dial(1).partialTurnPassedZero(-99)

partial(450)
partial(-450)
partial(50)
partial(-50)
partial(0)
partial(99)
partial(-99)
partial(100)
partial(101)

passwordv2(testLines)
passwordv2(lines)
