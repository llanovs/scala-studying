package demo3

import java.time.Instant

import demo3.ControlStructuresHomework.ErrorMessage

import scala.util.{Failure, Success, Try}

object Functions extends App {

  // Functions are expressions that have parameters, and take arguments.

  // Functions are first-class values:
  // a functions can be assigned to a value, passed as a parameter and returned as a result

  // first order functions take and return ordinary data types
  // higher order functions take and/or return other functions
  
  // Pure functions are mappings between two sets

  // A function is impure if ..
  // - is not defined for all values of input type
  // - throws an exception
  // - returns a value that depends on something else than an input value
  // - works with mutable shared state
  // - does something that is not present in the function signature (side effects)
  // - relies on reflection

  // Why is Null bad?
  // null causes NullPointerException
  // null cannot be removed from the language (although Scala 3 will help handle it)
  // `null` can be passed anywhere

  // Pure function should:
  // - be deterministic
  // - not have side effects
  // - be total (not partial)
  // - not throw exceptions
  // - not do any mutation (local, non-local, reference, etc.)
  // - not use `null`

  // A function without side effects only returns a value
  
  // Exercise.
  // Implement `isEven` a function that checks if a number is even
  def isEven(x: Int): Boolean = x % 2 == 0

  // Implement `isEvenVal` which behaves exactly like `isEven`.
   val isEvenVal: Int => Boolean = _ % 2 == 0
  
  // Implement `isEvenDefToVal` by transforming `isEven` def function into a val
   val isEvenDefToVal: Int => Boolean = isEven

  // Exercise.
  // Implement `mapOption` a function. Do not use scala option api
  def mapOption[A, B](option: Option[A], f: A => B): Option[B] = option match {
    case Some(value) => Some(f(value))
    case None => None
  }

  // Exercises. Convert the following function into a pure function.
  def parseDate(s: String): Instant = Instant.parse(s)
  def parseDatePure(s: String): Either[Throwable, Instant] = Try(Instant.parse(s)).toEither

  //
  def divide(a: Int, b: Int): Int = a / b
  def dividePure(a: Int, b: Int): Either[ErrorMessage, Double] = b match {
    case 0 => Left(ErrorMessage("Divide by 0"))
    case _ => Right(a.toDouble / b)
  }

  //
  def isAfterNow(date: Instant): Boolean = date.isAfter(Instant.now())
  def isAfterNowPure(dataNow: () => Instant, data: Instant): Boolean = data.isAfter(dataNow())

  //
  case class Nel[T](head: T, rest: List[T])
  def nel[T](list: List[T]): Nel[T] = {
    if (list.isEmpty) println("ERROR: provide non empty list")
    Nel(list.head, list.tail)
  }
  def nelPure[T](list: List[T]): Either[ErrorMessage, Nel[T]] = {
    list match {
      case Nil => Left(ErrorMessage("ERROR: provide non empty list"))
      case _ => Right(Nel(list.head, list.tail))
    }
  }
}
