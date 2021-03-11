package demo
import Array._

object Arrays {

  var arr: Array[Int] = new Array[Int](5);
  var arr2 = new Array[Int](5);

  def main(args: Array[String]): Unit = {
    arr(0) = 230
    arr(1) = 20
    arr(2) = 120
    arr(3) = 420
    arr(4) = 320

    //iterate array
    for(elem <- arr){
      println(elem)
    }

    //iterate array
    for(i <- arr2.indices){
      arr2(i) = i + 5
    }

    //iterate array
    arr2.foreach(println);

    //array with init values
    var arr3 = Array(2,5,1,6,1,7);
    arr3.foreach(println);

    //concat arrays
    val arr4 = concat(arr, arr2)
    arr4.foreach(println);
  }

}
