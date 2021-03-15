package demo2

object Implicits extends App {

  //The Implicit Modifier

  abstract class Action[T] {

    def unit: T

    def add(x: T, y: T): T

    def sub(x: T, y: T): T

    def multiply(x: T, y: T): T

    def divide(x: T, y: T): T

    def pow(x: T, y: T): T

  }

  implicit object IntAction extends Action[Int] {

    override def unit: Int = 0

    override def add(x: Int, y: Int): Int = x + y

    override def sub(x: Int, y: Int): Int = x - y

    override def multiply(x: Int, y: Int): Int = x * y

    override def divide(x: Int, y: Int): Int = x / y

    override def pow(x: Int, y: Int): Int = Math.round(Math.pow(x.toDouble, y.toDouble)).toInt
  }

}
