package demo3

object Basic extends App {

  //Exercise 1. List all boolean
  val allBooleans: Set[Boolean] = Set(true, false);

  //Exercise 2. Implement a simple method
  def helloMethod(name: String) = s"Hello, $name!"
  println(helloMethod("Mary"))

  def add(x: Int, y: Int = 2) = x + y;
  println(add(y = 4, x = 2))

  //Exercise 3. Implement a simple method that use helloMethod
  val helloFunction: String => String = (name: String) => helloMethod(name)
  println(helloFunction("Anna"))


  //Example of a function returned as a return value
  def formatNamedDouble(name: String, format: Double => String): Double => String = { x: Double =>
    s"$name = ${format(x)}"
  }
  val fourDecimalPlaces: Double => String = (x: Double) => f"$x%.4f"
  val formattedNamedDouble: String = formatNamedDouble("x", fourDecimalPlaces)(Math.PI) // x = 3.1416
  println(formattedNamedDouble)

  //Exercise 4. Implement `power` method which takes a Byte `n` and returns a function from Int to
  // Long, raising the Int parameter provided to the n-th power using `Math.pow`.
  // For conversions, use `Double#round` (for rounding Double-s to Long-s) as well as `Byte` and `Int`
  // `toDouble` (for converting Byte-s and Int-s to Double-s).

  def power(n: Byte): Int => Long = {
    x: Int => Math.pow(x.toDouble, n.toDouble).toLong
  }

  // Homework. Implement functions that calculate
  // https://en.wikipedia.org/wiki/Least_common_multiple and
  // https://en.wikipedia.org/wiki/Greatest_common_divisor for integers.

  def lcd(a: Int, b: Int): Int = {
    Math.abs(a * b)/gcd(a, b)
  }

  def gcd(a: Int, b: Int): Int =   {
    if(a == 0 || b == 0) 0
    else if(a % b == 0)  b
    else gcd(b, a % b)
  }

  def lcd(a: Int, b: Int, c: Int): Int = lcd(lcd(a, b), c)

  println(gcd(8,12)) //4
  println(gcd(54,24)) //6
  println(gcd(0,0)) //0

  println(lcd(21,6)) //42
  println(lcd(8,9,21)) //504
}
