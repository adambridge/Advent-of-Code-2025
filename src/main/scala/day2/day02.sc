import scala.io.Source

def getPairs(fileName: String): List[(String, String)] = {
  val resource = Source.fromResource(fileName)
  val testInput: String = resource.getLines.toList.head
  val ranges: List[String] = testInput.split(",").toList
  ranges.map { r =>
    val arr = r.split("-")
    (arr(0), arr(1))
  }
}

println(getPairs("test02.txt"))

// cool but unnecessary :-(
// composed "only" of ...
def substrs(id: String): List[String] = {
  val indexedSeq = for {
    start <- 0 until id.length
    end <- start + 1 to id.length
  } yield id.slice(start, end)
  indexedSeq.toList
}

substrs("abc")

def inHalf(s: String): (String, String) =
  (s.slice(0, s.length / 2), s.slice(s.length / 2, s.length))

inHalf("abcd")

def invalid(lo: Long, hi: Long): List[Long] = {
  val indexedSeq = for {
    id <- lo to hi
    s = id.toString
    if s.length % 2 == 0
    halves = inHalf(s)
    if halves._1 == halves._2
  } yield id
  indexedSeq.toList
}

invalid(11,22)
invalid(95, 115)

def sumInvalid(fileName: String): Long = {
  val invalidIds = for {
    (lo, hi) <- getPairs(fileName)
    id <- invalid(lo.toLong, hi.toLong)
  } yield id
  invalidIds.sum
}

sumInvalid("test02.txt")
sumInvalid("input02.txt")
