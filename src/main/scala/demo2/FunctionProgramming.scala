package demo2

object FunctionProgramming extends App {

  //Functional Programming
  //compose functions
  //pass functions as args
  //return function as a result
  //FunctionX == Function1....Function22
  //max available

  // FunctionX
  //the way how JVM works with function programming in Scala
  val simpleIncrementer = new Function1[Int, Int] {
    override def apply(arg: Int) : Int =  arg + 1
  }

  simpleIncrementer.apply(21) //22
  simpleIncrementer(21) //22
  //defined a function

  val stringConcatenator = new Function2[String, String, String] {
    override def apply(arg: String, arg1: String) : String =  arg.concat(arg1)
  }

  println(stringConcatenator.apply("Anna", "Jones"))

  //all scala functions are the instances of FunctionX type

  //syntax sugars
  val doubler: Function1[Int, Int] = (x: Int) => 2 * x
  println(doubler(4))

  //[Int, Int] the same is Int => Int
  //or just skip it
  val doubler2 = (x: Int) => 2 * x
  println(doubler2(4))

  //higher-order functions

  val map = List(1, 2, 3)
            .map(x => x + 6)
            .foreach(println)

  //make pairs 1, 2, 3 with a, b, c
  val pairs = List(1, 2, 3).flatMap(number =>
  List('a', 'b', 'c').map(letter => s"$number-$letter"))
  println(pairs)


  //the same with for-yield
  val pairs2 = for {
    number <- List(1, 2, 3)
    letter <- List('a', 'b', 'c')
  } yield s"$number-$letter"
  println(pairs2)

  //collections
  val list = List(1,2,3,4) //1,2,3,4
  val pList = 0::list //0,1,2,3,4
  val xList = 0 +: list :+ 5 //0,1,2,3,4.5

  //sequences
  val aseq: Seq[Int] = Seq(1,2,3) //Sec.apply(1,2,3)
  var accessedElem = aseq(1) //the element at index 1: 2

  //vectors = quickly sequences
  val vector = Vector(1,2,3)

  //ranges
  val r = 1 to 100

}
