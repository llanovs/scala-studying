package demo

object ListExample {
  //immutable
  var list: List[Int] = List(5,6,78);
  var list2 = List("Anna", "Tom", "Iren");

  def main(args: Array[String]): Unit = {

    println(list)
    println(list2)

    //add element to print, list doesn't changed
    println(0::list) //0,5,6,78
    println(list) //5,6,78
    println("Head: " + list.head)
    println("Tail: " + list.tail)
    println("Reverse: " + list.reverse)
    println("Fill: " + List.fill(5)(2))
    println("Max: " + list.max)
    var sum: Int = 0;
    list.foreach(sum += _)
    println("Sum: " + sum)
    println(1::5::9::Nil)//List with 1,5,9

    //iterate list
    list.foreach(println)

  }
}
