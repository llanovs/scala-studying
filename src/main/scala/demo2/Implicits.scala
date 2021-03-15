package demo2

import demo2.Implicits.{Donut, DonutString}
import DonutImplicits._

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


  //How To Create Function With Implicit Parameters
  
  def totalCost(donutType: String, quantity: Int)(implicit discount: Double): Double = {
    println(s"Calculating the price for $quantity $donutType")
    val totalCost = 2.50 * quantity * (1 - discount)
    totalCost
  }

  implicit val discount: Double = 0.1
  println(totalCost("Glazed Donut", 5))

  //How To Create Function With Multiple Implicit Parameters
  
  def totalCost2(donutType: String, quantity: Int)(implicit discount: Double, storeName: String): Double = {
    println(s"$storeName calculating the price for $quantity $donutType")
    val totalCost = 2.50 * quantity * (1 - discount)
    totalCost
  }
  
  implicit val storeName: String = "Tasty Donut Store"
  println(totalCost2("Glazed Donut", 5))
  
  //How To Create Implicit Function
  class DonutString(s: String) {
    def isFavoriteDonut: Boolean = s == "Glazed Donut"
  }
  
  val glazedDonut = "Glazed Donut"
  val vanillaDonut = "Vanilla Donut"

  implicit def stringToDonutString(s: String) = new DonutString(s)
  
  println(s"Is Glazed Donut my favorive: ${glazedDonut.isFavoriteDonut}")
  println(s"Is Vanilla Donut my favorite: ${vanillaDonut.isFavoriteDonut}")
  
  //How To Use Implicit Class

  case class Donut(name: String, price: Double, productCode: Option[Long] = None)

  val vanillaDonut2: Donut = Donut("Vanilla", 1.50)
  
  println(s"Vanilla donut uuid = ${vanillaDonut2.uuid}")
  
}

object DonutImplicits {
  implicit class AugmentedDonut(donut: Donut) {
    def uuid: String = s"${donut.name} - ${donut.productCode.getOrElse(12345)}"
  }
}

