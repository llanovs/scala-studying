package demo

object HigherOrderMethods {

  val list = List(1, 2, 3)
  val lst = List("A", "B", "C")
  val map = Map(1 -> "Tom", 2 -> "Max", 3 -> "John")

  def main(args: Array[String]): Unit = {

    //map
    //apply some function to each element of a list
    println("MAP")
    var list2 = list.map(_ * 2)
    println(list.map(_ * 2))
    println(list2)

    var list3 = list.map(item => item + 2)
    println(list3)

    println(list.map(x => "hi" * x))
    println(list.map(x => "hi" + x))

    println(map.map(x => "hi" + x))
    println(map.values.map(x => "hi" + x))
    println("Hello".map(_.toUpper))

    //flatten
    //combine the Lists
    println("FLATTEN")
    println(List(List(1,3,7), List(4,17,10)))
    println(List(List(1,3,7), List(4,17,10)).flatten)

    //flatMap
    // apply some function to each element of a list
    // and combine all lists in one list
    println("MAP")
    println(list.map(x => List(x, x + 1)))
    println(list.flatMap(x => List(x, x + 1)))

    //filter
    //apply some predicate function to each element of a list
    //choose only the value that fits condition
    println("FILTER")
    println(list.filter(x => x %2 == 0))

    //Apply binary operator to each element of collection
    //result of each step pass to the next step

    //reduce(reduce, reduceLeft, reduceRight)
    //apply some binary function to each element of a list
    println("REDUCE")
    println(lst.reduceLeft(_+_))

    println(lst.reduceLeft((x,y) => {
      println(x + "," + y);
      x + y;
    }))

    //results different
    println(list.reduceLeft(_-_)) // starts from left side
    println(list.reduceRight(_-_)) // starts from right side
    println(list.reduce(_-_)) // starts from left side

    println(list.sum)


    //fold(fold, foldLeft, foldRight)
    //the same as reduce method but has init argument
    //results different
    println("FOLD")
    println(list.foldLeft(0)(_-_)) // starts from left side
    println(list.foldRight(0)(_-_)) // starts from right side
    println(list.fold(0)(_-_)) // starts from left side
    println(lst.fold("Z")(_+_))

    //scan(scan, scanLeft, scanRight)
    //Apply binary operator to each element of collection
    //has init argument that involved to operation
    //give a map of intermedia result
    println("SCAN")
    println(list.scanLeft(0)(_-_)) // starts from left side
    println(list.scanRight(0)(_-_)) // starts from right side
    println(list.scan(0)(_-_)) // starts from left side
    println(lst.scan("Z")(_+_)) // List(Z, ZA, ZAB, ZABC)
  }
}
