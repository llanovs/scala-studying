package demo

object Function {

  def main(args: Array[String]): Unit = {
    //void function that use Unit type
    print(Math.print(5,60));

    println(Math.add(4, 5));

    //use only default values
    println(Math.sub());

    // use x = 60, y - default value
    println(Math.sub(60));

    //use your own values
    println(Math.sub(4, 5));

    println(Math.div(40, 5));
    println(Math.multiply(4, 5));

    //possible way
    println(Math.square(3));

    //also possible way when function has one argument
    println(Math square 3);

    println(Math.+(4, 5));

    var add = (x: Int, y: Int) => x + y;
    println(add(500,6))
  }

  object Math {

    def print(x: Int, y: Int): Unit = println(s"x = $x, y = $y");

    //variant 1
    def add(x: Int, y: Int): Int = {
      return x + y;
    }

    //variant 2
    //without return also work
    //has default parameters x = 5, y = 4
    def sub(x: Int = 5, y: Int = 4): Int = {
      x - y;
    }

    //variant 3
    //without return and without {} if it's one line function
    def multiply(x: Int, y: Int): Int = x * y;

    //variant 4
    //without return and without {} if it's one line function,
    //without returning type
    def div(x: Int, y: Int) = x / y;

    def square(x: Int) = x * x;

    //variant 5
    //use function name like +, * etc
    def +(x: Int, y: Int): Int = x + y;
  }
}