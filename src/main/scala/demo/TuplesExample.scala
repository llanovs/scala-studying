package demo

object TuplesExample {

  //immutable
  //contains values of different types
  val tuple = (1, 5.6, 56, 89.67, -1, "Anna", true);

  //use new TupleN where N - number of elements in tuple
  val tuple2 = new Tuple4(1, -1, "Anna", true);

  //valid only for 2 elements
  val tuple3 = (1 -> "Anna");

  //valid only for 2 elements
  val tuple4 = (1, "Anna", (2, 3));

  def main(args: Array[String]): Unit = {

    println(tuple)
    println(tuple2)

    println(tuple2._1)
    println(tuple2._4)
    println(tuple3)

    tuple.productIterator.foreach{
      item => println(item)
    }

    println(tuple4._3._1)

  }

}
