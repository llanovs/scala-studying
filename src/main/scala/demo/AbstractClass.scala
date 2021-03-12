package demo

object AbstractClass {

  def main(args: Array[String]): Unit = {
    var square = new Square(3);
    println(s"Area: ${square.area}")

    square.width = 5
    println(s"Area: ${square.area}")
  }
}
