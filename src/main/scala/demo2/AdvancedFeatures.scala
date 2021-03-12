package demo2

import scala.concurrent.Future
import scala.util.Success
import scala.util.Failure
import scala.util.Try
import scala.concurrent.ExecutionContext.Implicits.global

object AdvancedFeatures extends App{

  //laze evaluation
  //useful in infinite collections
  lazy val x = 5;
  lazy val foo = {
    println("I'm so lazy :)")
    32
  }

    println(foo + 3)

  //pseudo-collections: Option, Try
  def methodWhichCanReturnNull(): String = "Hello, Scala";

  if(methodWhichCanReturnNull() == null){
    //do smt
  }

  //Option
  val option = Option(methodWhichCanReturnNull())

  //1 variant
  val z = option match {
    case Some(string: String) => s"I have a valid value $string"
    case None => "I obtained nothing"
  }

  //2 variant
  val y = option.getOrElse("Something went wrong. Value: None")
  println(y)


  //Try
  def methodWhichCanReturnException(): String = throw new RuntimeException;

  //1 variant
  try {
    methodWhichCanReturnException()
  }catch{
    case e: Exception => println(e)
  }

  //2 variant
  val aTry = Try(methodWhichCanReturnException())
  var k = aTry.getOrElse("Something went wrong")
  println(k)

  //3 variant
  val r = aTry match {
    case Success(value) => s"I have a valid value: $value"
    case Failure(e) => s"I throw an exception $e"
  }
  println(r)

  //Asynchronous programming
  //Future (monads)
  val aFuture = Future{
    println("Loading...")
    Thread sleep(2000)
    println("I have a value")
    67
  }
  Thread sleep(2000)

  //Implicits
  //implicit arguments - compiler automatically find implicit value
  //and put it into function
  def methodWithImplicitsArgs(implicit arg:Int) = arg + 1;

  implicit val myImplicit = 46;
  println(methodWithImplicitsArgs) //methodWithImplicitsArgs(myImplicit)

  //implicit conversions
  //use carefully
  implicit class MyRichInteger(n:Int){
    def isEven() = n % 2 == 0
  }

  println(23.isEven())
}
