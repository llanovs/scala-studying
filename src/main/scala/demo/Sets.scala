package demo

object Sets {
  //immutable
  //all sets by default are immutable
  val set: Set[Int] = Set(4, 6, 8, 14);

  //to make set mutable use annotation
  //scala.collection.mutable.Set
  var set2 = scala.collection.mutable.Set(14, 86, 48, 999);
  val set3 = Set("Anna", "Tom", "John", "Andrew");

  def main(args: Array[String]): Unit = {
    println(set)
    println(set3)

    //add values to mutable set
    set2 + 10
    println(set2 + 10)
    println(set2)

    println("Is empty: " + set2.isEmpty)
    println("Head: " + set2.head)
    println("Tail: " + set2.tail)

    //concat sets
    //only unique values
    println(set ++ set2)

    //select the duplicates values in both sets
    println(set.&(set2))
    println(set.intersect(set2))

    //check if value is present in set
    println("Has "+ 10 +" in set: " + set2(10))
    println("Has "+ 86 +" in set: " + set2(86))
    println("Has Anna in set: " + set3("Anna"))

    println(set.max)
    println(set.min)
    println(set.filter(item => item < 10))
  }
}
