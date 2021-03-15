package demo3

object ClassesAndTraits extends App {

  sealed trait Shape extends Located with Bounded with Movable

  sealed trait Located {
    def x: Double

    def y: Double
  }

  sealed trait Bounded {
    def minX: Double

    def maxX: Double

    def minY: Double

    def maxY: Double
  }

  final case class Point(x: Double, y: Double) extends Shape {
    override def minX: Double = x

    override def maxX: Double = x

    override def minY: Double = y

    override def maxY: Double = y

    override def move(dx: Double, dy: Double): Point = {
      new Point(x + dx, y + dy);
    }
  }

  final case class Circle(centerX: Double, centerY: Double, radius: Double) extends Shape {
    override def x: Double = centerX

    override def y: Double = centerY

    override def minX: Double = centerX - radius

    override def maxX: Double = centerX + radius

    override def minY: Double = centerY - radius

    override def maxY: Double = centerY + radius

    override def move(dx: Double, dy: Double): Circle = {
      new Circle(x + dx, y + dy, radius);
    }
  }

  // Exercise. Implement an algorithm for finding the minimum bounding rectangle
  // (https://en.wikipedia.org/wiki/Minimum_bounding_rectangle) for a set of `Bounded` objects.
  def minimumBoundingRectangle(objects: Set[Bounded]): Bounded =
    new Bounded {
      implicit private val doubleOrdering: Ordering[Double] = Ordering.Double.IeeeOrdering

      override def minX: Double = objects.map(_.minX).min

      override def maxX: Double = objects.map(_.maxX).max

      override def minY: Double = objects.map(_.minY).min

      override def maxY: Double = objects.map(_.maxY).max
    }

  // Exercise. Add another Shape class called Rectangle and check that the compiler catches that we are
  // missing code to handle it in `describe`.

  final case class Rectangle(centerX: Double, centerY: Double, width: Double, height: Double) extends Shape {
    override def x: Double = centerX

    override def y: Double = centerY

    override def minX: Double = centerX - width / 2

    override def maxX: Double = centerX + width / 2

    override def minY: Double = centerY - height / 2

    override def maxY: Double = centerY + width / 2

    override def move(dx: Double, dy: Double): Rectangle = {
      new Rectangle(x + dx, y + dy, width, height);
    }
  }

  val rectangle = Rectangle(50, 50, 100, 50)


  // TODO: Exercise. Add another Shape class called Rectangle and check that the compiler catches that we are
  // missing code to handle it in `describe`.

  // Let us come back to our `Shape`-s and add a `Movable` trait
  // which will have a method:
  //
  //   def move(dx: Double, dy: Double)
  //
  // How should we implement `move` for various types?
  //
  // What should be the return type of the `move` method in `Shape`? In `Point` and other sub-types?

  sealed trait Movable {
    def move(dx: Double, dy: Double): Movable
  }

  //define a `Stack[A]` which works with any type of element `A`
  final case class Stack[A](elements: List[A] = Nil) {
    def push(x: A): Stack[A] = Stack(x :: elements)

    def peek: Option[A] = elements.headOption

    def pop: Option[(A, Stack[A])] = elements match {
      case Nil => None
      case x :: xs => Some(x, Stack(xs))
    }
  }

  List(1, 2, 5)
  1 :: 2 :: 5 :: Nil

  // Homework
  //
  // Add additional 2D shapes such as triangle and square.
  //
  // In addition to the 2D shapes classes, add also 3D shapes classes
  // (origin, point, sphere, cube, cuboid, 3D triangle - you can add
  // others if you think they are a good fit).
  //
  // Add method `area` to 2D shapes.
  //
  // Add methods `surfaceArea` and `volume` to 3D shapes.
  //
  // If some of the implementation involves advanced math, it is OK
  // to skip it (leave unimplemented), the primary intent of this
  // exercise is modelling using case classes and traits, and not math.



  // Exercise. Implement a "Fizz-Buzz" https://en.wikipedia.org/wiki/Fizz_buzz function using the if-then-else,
  // returning "fizzbuzz" for numbers which divide with 15, "fizz" for those which divide by 3 and "buzz" for
  // those which divide with 5, and returning the input number as a string for other numbers:
  def fizzBuzz1(n: Int): String = {
    if (n % 15 == 0) "fizzbuzz"
    else if (n % 3 == 0) "fizz"
    else if (n % 5 == 0) "buzz"
    else n.toString
  }

  // Exercise. Implement a "Fizz-Buzz" function using pattern matching:
  def fizzBuzz2(n: Int): String = n match {
    case n if (n % 15 == 0) => "fizzbuzz"
    case n if (n % 3 == 0) => "fizz"
    case n if (n % 5 == 0) => "buzz"
    case _ => n.toString
  }
  
  //or using Tuple
  def fizzBuzz3(n: Int): String = (n % 3, n % 5) match {
    case (0, 0) => "fizzbuzz"
    case (_, 0) => "fizz"
    case (0, _) => "buzz"
    case _ => n.toString
  }
  
}
