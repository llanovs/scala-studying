package demo

object LazyEvaluation {

  class Strict{
    val e = {
      println("strict")
      9
    }
  }

  class Lazy{
    lazy val t = {
      println("lazy")
      9
    }
  }

  def main(args: Array[String]): Unit = {

    // lazy evaluation(call-by-need) is an evaluation strategy
    // which delays the evaluation of an expression until
    // its value is needed
    //in Scala it's strict evaluation but it can be change

    //in terminal
    //open you project
    //use command scala
    //when you create a variable use key word lazy before var/val
    lazy val t = 5;

    //In this case lazy val t: Int // unevaluated
    //but when you try to use this variable it will be evaluate
    //val res0: Int = 5
    println(t)

    val x = new Strict;
    val y = new Lazy;// unevaluated
    println(x.e)
    println(y.t)//evaluated

    //call by name
    println("Call by name")

    val add = (a: Int, b: Int) => {
      println("Add")
      a + b
    }
    method1(add(5,6))
    method2(add(5,6))
  }

  def method1(n: Int): Unit ={
    println(s"Method 1 $n")
  }

  def method2(n: => Int): Unit ={
    //use lazy evaluation
    println("Method 2")
    println(n)
  }
}
