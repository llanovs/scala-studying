package demo3

import demo3.Implicits.Task.List

import scala.util.Random

object Implicits extends App {

  // Implicits: implicit parameters
  implicit val name: String = "Oleg"
  LuckService.greet
  LuckService.predictLuck
  LuckService.bye

  object LuckService {
    def greet(implicit name: String): Unit = println(s"Hello $name")
    def predictLuck(implicit name: String): Unit = println(s"Your luck is ${Random.nextInt(11)} today, $name")
    def bye(implicit name: String): Unit = println(s"See you $name")
  }

  object ImplicitParamTask {

    object Task1 {

      final case class User(id: String)

      trait DbConnection

      object DbConnection {
        def apply(): DbConnection = new DbConnection {}
      }

      // make second argument implicit
      def createUser(user: User) (implicit connection: DbConnection): Unit =
        println("user created")

      implicit val connection: DbConnection = DbConnection()
      createUser(User("123"))
    }

    object Task2 {
      final case class Money(amount: Int)
      val list: List[Money] = ???
      //    oh no, i won't compile
      //    list.sorted
    }
  }

    // Implicits: implicit conversions
    object BasicImplicitConversion extends App {
      case class A(x: Int)
      case class B(x: Int)

      implicit def conversion(a: A): B = B(a.x)

      def myMethod(b: B): Unit = println(b)

      myMethod(A(123))
    }


  object ImplicitConversionForSyntax extends App {

    // stupid class used only for adding a new method to an existing type
    case class MyTupleExt(t: (Int, Int)) {
      def double: (Int, Int) = {
        val (x, y) = t
        (2 * x, 2 * y)
      }
    }

    // an implicit conversion
    implicit val nameDoesntMatter: ((Int, Int)) => MyTupleExt = MyTupleExt

    // nobody expects MyTupleExt as param so it is used only for adding new methods
    (1, 2).double
  }

  object ImplicitConversionsSugar extends App {

    println((4, 5).double)

    implicit class TupleExt(x: (Int, Int)) {
      def double: (Int, Int) = (2 * x._1, 2 * x._2)
    }

  }


  // write an implicit class so the next line compiles
  // val b: Boolean = List(true, true).allTrue
  object Task {
    implicit class List[Boolean](item: (Boolean, Boolean)) {
       def allTrue = item._1 == true && item._1 == item._2
    }
  }

  println(List(true, true).allTrue)
  println(List(true, false).allTrue)
  println(List(false, true).allTrue)
  println(List(false, false).allTrue)
}