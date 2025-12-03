package day01

import org.scalatest._
import flatspec._
import matchers.should._

class DialSpec extends AnyFlatSpec with Matchers {
  "A Dial" should "turn as per part 1 examples" in {
    Dial(11).turn(8) should be (Dial(19))
    Dial(11).turn(8).turn(-19) should be(Dial(0))
  }

  it should "be a circle" in {
    Dial(0).turn(-1) should be (Dial(99))
    Dial(99).turn(1) should be(Dial(0))
  }
}
