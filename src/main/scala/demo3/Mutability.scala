package demo3

//lesson 4
object Mutability extends App {

  /*
     In a sorted list find two numbers which have a gap between
        None for List(1, 2, 3, 4)
        Some((2, 8)) for List(1, 2, 8)
   */
  def findGap(l: List[Int]): Option[(Int, Int)] = {
    if (l.isEmpty) None
    else {
      val pair = l zip l.tail
      val result = pair.filter(p => p._2 > p._1 + 1) 
      // not good variant because of usng p._1 etc
      if (result.isEmpty) None
      else Some(result(0))
    }
  }

  def findGap2(l: List[Int]): Option[(Int, Int)] = {
    l match {
      case Nil => None
      case _ :: x => (l zip x).find({
        case (n1, n2) => n2 > n1 + 1
      })
    }
  }

  println(findGap(List(1, 2, 3, 4)))
  println(findGap(List(1, 2, 8)))

  //reduce
  def min(list: List[Int]): Option[Int] = {
    list match {
      case Nil => None
      case _ => Some(list.reduce((a, b) => math.min(a,b)))
    }
  }

  //fold
  def min2(list: List[Int]): Option[Int] = {
    list match {
      case Nil => None
      case _ => Some(list.fold(list.head)((a, b) => math.min(a,b)))
    }
  }

  //minOption
  def min3(list: List[Int]): Option[Int] = {
    list match {
      case Nil => None
      case _ => list.minOption
    }
  }
}
