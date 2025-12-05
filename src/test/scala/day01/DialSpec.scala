package day01

import org.scalatest.*
import flatspec.*
import matchers.should.*

import scala.io.Source

class DialSpec extends AnyFlatSpec with Matchers {
  "A Dial" should "turn as per part 1 examples" in {
    Dial(11).turn(8) should be (Dial(19))
    Dial(11).turn(8).turn(-19) should be(Dial(0))
  }

  it should "be a circle" in {
    Dial(0).turn(-1) should be (Dial(99))
    Dial(99).turn(1) should be (Dial(0))

    Dial(5).turn(-10) should be (Dial(95))
    Dial(95).turn(5) should be (Dial(0))

    Dial(0).turn(100) should be (Dial(0))
    Dial(0).turn(-100) should be (Dial(0))
  }

  "The test input" should "have a password of 3" in {
    val lines: List[String] = Source.fromResource("test01.txt").getLines.toList
    Dial.password(lines) should be (3)
  }

  "The part 1 input" should "have a password of 980" in {
    val testResource = Source.fromResource("input01.txt")
    val testLines: List[String] = testResource.getLines.toList

    Dial.password(testLines) should be(980)
  }

  /*
  The dial starts by pointing at 50.
The dial is rotated L68 to point at 82; during this rotation, it points at 0 once.
The dial is rotated L30 to point at 52.
The dial is rotated R48 to point at 0.
The dial is rotated L5 to point at 95.
The dial is rotated R60 to point at 55; during this rotation, it points at 0 once.
The dial is rotated L55 to point at 0.
The dial is rotated L1 to point at 99.
The dial is rotated L99 to point at 0.
The dial is rotated R14 to point at 14.
The dial is rotated L82 to point at 32; during this rotation, it points at 0 once.
   */
  "A Dial" should "count zeros passed #1" in {
    Dial(50).turn(-68) should be(Dial(82))
    Dial(82).zerosPassed(-68) should be(1)
  }

  it should "count zeros passed #2" in {
    Dial(82).turn(-30) should be (Dial(52))
    Dial(52).zerosPassed(-30) should be (0)
  }

  it should "count zeros passed #3" in {
    Dial(52).turn(48) should be(Dial(0))
    Dial(0).zerosPassed(48) should be(1)
  }

  it should "count zeros passed #4" in {
    Dial(0).turn(-5) should be(Dial(95))
    Dial(95).zerosPassed(-5) should be(0)
  }

  it should "count zeros passed #5" in {
    Dial(95).turn(60) should be(Dial(55))
    Dial(55).zerosPassed(60) should be(1)
  }

  it should "count zeros passed #6" in {
    Dial(55).turn(-55) should be(Dial(0))
    Dial(0).zerosPassed(-55) should be(1)
  }

  it should "count zeros passed #7" in {
    Dial(0).turn(-1) should be(Dial(99))
    Dial(99).zerosPassed(-1) should be(0)
  }

  it should "count zeros passed #8" in {
    Dial(99).turn(-99) should be(Dial(0))
    Dial(0).zerosPassed(-99) should be(1)
  }

  it should "count zeros passed #9" in {
    Dial(0).turn(14) should be(Dial(14))
    Dial(14).zerosPassed(14) should be(0)
  }

  it should "count zeros passed #10" in {
    Dial(14).turn(-82) should be(Dial(32))
    Dial(32).zerosPassed(-82) should be(1)
  }

  "The test input" should "have a v2 password of 6" in {
    val lines: List[String] = Source.fromResource("test01.txt").getLines.toList
    Dial.passwordv2(lines) should be(6)
  }
}
