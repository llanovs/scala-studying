package demo

object Closures {

  var number = 10;

  val add = (x: Int) => {
    number = x + number
    number //return number
  };

  def main(args: Array[String]): Unit = {

    println(add(12));

    number = 100;
    println(add(12));
    println(number);

  }

}
