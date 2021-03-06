package demo3

import scala.io.Source
import cats.implicits._
import demo3.ControlStructuresHomework.Command._
import demo3.ControlStructuresHomework.{ErrorMessage, ResultImpl}

object ControlStructuresHomework {

  // Homework

  // Create a command line application that reads various "commands" from the
  // stdin, evaluates them, and writes output to stdout.

  // Commands are:

  //   divide 4 5
  // which should output "4 divided by 5 is 0.8"

  //   sum 5 5 6 8.5
  // which should output "the sum of 5 5 6 8.5 is 24.5"

  //   average 4 3 8.5 4
  // which should output "the average of 4 3 8.5 4 is 4.875"

  //   min 4 -3 -17
  // which should output "the minimum of 4 -3 -17 is -17"

  //   max 4 -3 -17
  // which should output "the maximum of 4 -3 -17 is 4"

  // In case of commands that cannot be parsed or calculations that cannot be performed,
  // output a single line starting with "Error: "

  sealed trait Command {
    def action(): Either[ErrorMessage, Result]

    def renderResult(x: Result): String
  }

  object Command {

    final case class Divide(dividend: Double, divisor: Double) extends Command {

      override def action: Either[ErrorMessage, Result] = {
        divisor match {
          case 0 => Left(ErrorMessage("Error: Operation doesn't allow."))
          case _ => Right(ResultImpl((dividend / divisor).toString))
        }
      }

      override def renderResult(x: Result): String = s"$dividend divided by $divisor is ${x.getResult()}"
    }

    final case class Sum(numbers: List[Double]) extends Command {

      override def action: Either[ErrorMessage, Result] = Right(ResultImpl(numbers.sum.toString))

      override def renderResult(x: Result): String = {
        val numbersString = numbers.fold("")((a, b) => a + " " + b)
        s"the sum of$numbersString is ${x.getResult()}"
      }
    }

    final case class Average(numbers: List[Double]) extends Command {

      override def action: Either[ErrorMessage, Result] = {
        numbers.size match {
          case 0 => Left(ErrorMessage("Error: Operation doesn't allow."))
          case _ => Right(ResultImpl((numbers.sum / numbers.size).toString))
        }
      }

      override def renderResult(x: Result): String = {
        val numbersString = numbers.fold("")((a, b) => a + " " + b)
        s"the average of$numbersString is ${x.getResult()}"
      }
    }

    final case class Min(numbers: List[Double]) extends Command {

      override def action: Either[ErrorMessage, Result] = Right(ResultImpl(numbers.min.toString))

      override def renderResult(x: Result): String = {
        val numbersString = numbers.fold("")((a, b) => a + " " + b)
        val result = s"the minimum of$numbersString is ${x.getResult()}"
        result
      }
    }

    final case class Max(numbers: List[Double]) extends Command {

      override def action: Either[ErrorMessage, Result] = Right(ResultImpl(numbers.max.toString))

      override def renderResult(x: Result): String = {
        val numbersString = numbers.fold("")((a, b) => a + " " + b)
        s"the maximum of$numbersString is ${x.getResult()}"
      }
    }
  }

  final case class ErrorMessage(value: String)
  
  sealed trait Result {
    def getResult(): String;
  }

  final case class ResultImpl(value: String) extends Result {
    def getResult(): String = value;
  }

  def parseCommand(x: String): Either[ErrorMessage, Command] = {
    import demo3.ControlStructuresHomework.Command._

    val data: List[String] = x.split(" ").toList;

    val eithers: Seq[Either[String, Double]] = data.drop(1)
      .map(x => x.toDoubleOption.toRight(s"Error: Failed to parse $x"))
    
    val numbers = eithers collect {
      case Right(x) => x;
    }
    
    data(0) match {
      case "divide" => Right(Divide(numbers.head, numbers.tail.head))
      case "sum" => Right(Sum(numbers.toList))
      case "average" => Right(Average(numbers.toList))
      case "min" => Right(Min(numbers.toList))
      case "max" => Right(Max(numbers.toList))
      case _ => Left(ErrorMessage("Error: Operation not found"))
    }
  }

  // should return an error (using `Left` channel) in case of division by zero and other
  // invalid operations
  def calculate(x: Command): Either[ErrorMessage, Result] = {
    x.action()
  }


  def process(x: String): String = {
    import cats.implicits._

    val parsed = parseCommand(x);

    parsed match {
      case Left(errorMessage) => errorMessage.toString
      case Right(value) => {
        calculate(value) match {
          case Right(result) => value.renderResult(result)
          case Left(errorMessage) => errorMessage.toString
        }
      }
    }
  }

  // This `main` method reads lines from stdin, passes each to `process` and outputs the return value to stdout
  def main(args: Array[String]): Unit = Source.stdin.getLines() map process foreach println
  
  //todo: 
  //1) fixed parseCommand in part of wrong values
  //2) fixed renderResult in part of render int values
}