package demo

object FunctionCurring {

  def add(x: Int, y: Int)  = x + y

  // Function Curring
  def add2(x: Int) = (y: Int) => x + y;

  //simple signature of Function Curring
  def add3(x: Int)(y: Int) = x + y

  def main(args: Array[String]): Unit = {
      println(add(1,12))

      //Function Curring
      println(add2(1)(12))

      val sum = add2(40) // sum is a function
      println(sum(2))

      //simple signature of Function Curring
      println(add3(1)(12))

      var sum2 = add3(50)_;
      println(sum2(5))

  }
}
