package demo3

object HW5 extends App {

  // Additional exercises:
  // https://www.scala-exercises.org/std_lib/higher_order_functions
  //Task 1
  def lambda = { x: Int => x + 1 }
  def lambda2 = (x: Int) => x + 2
  val lambda3 = (x: Int) => x + 3

  val lambda4 = new Function1[Int, Int] {
    def apply(v1: Int): Int = v1 - 1
  }

  def lambda5(x: Int) = x + 1

  val result = lambda(3)
  val result1andhalf = lambda.apply(3)

  val result2 = lambda2(3)
  val result3 = lambda3(3)
  val result4 = lambda4(3)
  val result5 = lambda5(3)

  //  result should be 4
  //  result1andhalf should be 4
  //  result2 should be 5
  //  result3 should be 6
  //  result4 should be 2
  //  result5 should be 4

  //Task 2
  def lambda6 = (x: Int) => x + 1
  def result6 = lambda6(5)
  
  //  result6 should be 6
  
  
  //Task 3
  var incrementer = 1
  def closure = { x: Int => x + incrementer }
  val result1 = closure(10)
  //  result1 should be 11

  incrementer = 2
  val result7 = closure(10)
  //  result7 should be 12

  
  //Task 4
  def summation(x: Int, y: Int => Int) = y(x)
  
  var incrementer2 = 3
  def closure2 = (x: Int) => x + incrementer2

  val result8 = summation(10, closure2)
  //  result8 should be 13

  incrementer2 = 4
  val result9 = summation(10, closure2)
  //  result9 should be 14
  
  //Task 5
  def addWithoutSyntaxSugar(x: Int): Function1[Int, Int] = {
    new Function1[Int, Int]() {
      def apply(y: Int): Int = x + y
    }
  }
  addWithoutSyntaxSugar(1).isInstanceOf[Function1[Int, Int]] //should be true
  addWithoutSyntaxSugar(2)(3) //should be 5

  //Task 6
  def fiveAdder2: Function1[Int, Int] = addWithoutSyntaxSugar(5)
  fiveAdder2(5) //should be 10

  def addWithSyntaxSugar1(x: Int) = (y: Int) => x + y

  addWithSyntaxSugar1(1).isInstanceOf[Function1[Int, Int]] //should be true
  addWithSyntaxSugar1(2)(3) //should be 5
  
  def fiveAdder1 = addWithSyntaxSugar1(5)
  fiveAdder1(5) //should be 10

  //Task 7
  def addWithSyntaxSugar2(x: Int) = (y: Int) => x + y
  addWithSyntaxSugar2(1).isInstanceOf[Function1[_, _]] //should be true

  //Task 8
  def makeUpper(xs: List[String]) =
    xs map {
      _.toUpperCase
    }

  def makeWhatEverYouLike(xs: List[String], sideEffect: String => String) =
    xs map sideEffect

  makeUpper(List("abc", "xyz", "123")) // should be List("ABC", "XYZ", "123")

  makeWhatEverYouLike(List("ABC", "XYZ", "123"), x => x.toLowerCase) // should be List("abc", "xyz", "123")

  //using it inline
  val myName = (name: String) => s"My name is $name"
  makeWhatEverYouLike(List("John", "Mark"), myName) // should be List("My name is John", "My name is Mark")

  List("Scala", "Erlang", "Clojure") map (_.length) // should be List(5, 6, 7)
  
  
  // https://www.scala-exercises.org/fp_in_scala/getting_started_with_functional_programming

  //Exercise 2.1
  def fib(n: Int): Int = {
    @annotation.tailrec
    def loop(n: Int, prev: Int, cur: Int): Int =
      if (n <=
        0
      ) prev
      else loop(n - 1, cur, prev + cur)
    loop(n, 0, 1)
  }

  fib(5) //should be(5)
  
  //Exercise 2.2
  def isSorted[A](as: Array[A], ordering: (A, A) => Boolean): Boolean = {
    @annotation.tailrec
    def go(n: Int): Boolean =
      if (n >= as.length - 1) true
      else if (!ordering(as(n), as(n + 1))) false
      else go(n + 1)

    go(0)
  }

  isSorted(Array(1, 3, 5, 7), (x: Int, y: Int) => x < y) //shouldBe true

  isSorted(Array(7, 5, 1, 3), (x: Int, y: Int) => x > y) //shouldBe false

  isSorted(Array("Scala", "Exercises"),
    (x: String, y: String) => x.length < y.length) //shouldBe true

  //Exercise 2.3
  def curry[A, B, C](f: (A, B) => C): A => (B => C) =
    a => b => f(a, b)

  def f(a: Int, b: Int): Int = a + b
  def g(a: Int)(b: Int): Int = a + b

  curry(f)(1)(1) == f(1, 1) // shouldBe true
  curry(f)(1)(1) == g(1)(1) // shouldBe true


  //Exercise 2.4
  def uncurry[A, B, C](f: A => B => C): (A, B) => C =
    (a, b) => f(a)(b)

  def f1(a: Int, b: Int): Int = a + b
  def g1(a: Int)(b: Int): Int = a + b

  uncurry(g1)(1, 1) == g1(1)(1) //shouldBe true
  uncurry(g1)(1, 1) == f1(1, 1) //shouldBe true

  
  //Exercise 2.5
  def compose[A, B, C](f2: B => C, g2: A => B): A => C =
    a => f2(g2(a))

  def f2(b: Int): Int = b / 2
  def g2(a: Int): Int = a + 2

  compose(f2, g2)(0) == compose(g2, f2)(0) //shouldBe false
  compose(f2, g2)(2) //shouldBe 2
  compose(g2, f2)(2) //shouldBe 3


  // Homework. Define all algebraic data types, which would be needed to implement “Hold’em Hand Strength”
  // task you completed to join the bootcamp. Use your best judgement about particular data types to include
  // in the solution, you can model concepts like:
  //
  // 1. Suit
  // 2. Rank
  // 3. Card
  // 4. Hand (Texas or Omaha)
  // 5. Board
  // 6. Poker Combination (High Card, Pair, etc.)
  // 7. Test Case (Board & Hands to rank)
  // 8. Test Result (Hands ranked in a particular order for a particular Board, accounting for splits)
  //
  // Make sure the defined model protects against invalid data. Use value classes and smart constructors as
  // appropriate. Place the solution under `adt` package in your homework repository.

  // Attributions and useful links:
  // https://nrinaudo.github.io/scala-best-practices/definitions/adt.html
  // https://alvinalexander.com/scala/fp-book/algebraic-data-types-adts-in-scala/
  // https://en.wikipedia.org/wiki/Algebraic_data_type
}
