package demo

import scala.annotation._

object Recursion {

  def main(args: Array[String]): Unit = {
    println(gcd(12,18))

    println(sum(999999, 1))
  }

  //recursion
  def gcd(a: Int, b: Int): Int = {
    if(b == 0) a
      else gcd(b, a%b)
  }

  //Tail recursion
  //recursive call is the last operation performed
  //no operation or data saved when call return
  //compiler can reuse stack frame to convert
  //recursion to a loop
  def sum(number: Int, result: Int): Int = {
    if(number == 1) result
    else sum(number - 1, result +number)
  }

  //@tailrec Annotation
  //help to figure out tail recursive calls
  //fail at compile time if something wrong
  //import scala.annotation._
  //@tailrec over method call

// Will fail at compile because of " + number" part
//
//  @tailrec
//  def sum(number: Int): Int = {
//    if(number == 1) 1
//    else sum(number - 1) + number
//  }
}
