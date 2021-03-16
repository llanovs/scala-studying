package demo3

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.{ErrorMessages, ErrorMsg}

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

object ControlStructures extends App {

  // Exercise: Implement a function `applyNTimes` which takes a function `f` and an integer `n` and
  // returns a function which applies the function `f` `n` times.
  //
  // Thus `applyNTimesForInts(_ + 1, 4)(3)` should return `((((3 + 1) + 1) + 1) + 1)` or `7`.

  //using recursion
  def applyNTimesForInts(f: Int => Int, n: Int): Int => Int = {
    //x - it's a number which we will use as a bacis value
    //so for example if x = 3 than (3 + 1) + 1) + 1) + 1 = 7
    x: Int =>
      if (n == 0) x
      else applyNTimesForInts(f, n = n - 1)(f(x))
  }

  println(applyNTimesForInts(_ + 1, 4)(3)) //7

  //using tail recursion
  def applyNTimesForInts2(f: Int => Int, n: Int): Int => Int = {
    x: Int =>
      @tailrec
      def helper(n: Int, acc: Int): Int =
        if (n == 0) acc
        else helper(n - 1, f(acc))

      helper(n, x)
  }

  println(applyNTimesForInts2(_ + 1, 4)(3)) //7

  //using foldLeft
  def applyNTimesForInts3(f: Int => Int, n: Int): Int => Int = {
    x: Int =>
      (1 to n).foldLeft(x)((acc, _) => f(acc))
  }

  println(applyNTimesForInts3(_ + 1, 4)(3)) //7

  // Exercise: Convert the function `applyNTimesForInts` into a polymorphic function `applyNTimes`:
  def applyNTimes[A](f: A => A, n: Int): A => A = { x: A =>

    @tailrec
    def helper(n: Int, acc: A): A =
      if (n == 0) acc
      else helper(n - 1, f(acc))

    helper(n, x)
  }

  // Exercise:
  //
  // Given:
  val A = Set(0, 1, 2)
  val B = Set(true, false)

  // List all the elements in `A * B`.
  // Use a "for comprehension" in your solution.

  val AProductB: Set[(Int, Boolean)] = for {
    x <- A
    y <- B
  } yield Tuple2(x, y)

  println(AProductB)

  // List all the elements in `A + B`.
  // Use "map" and `++` (`Set` union operation) in your solution.
  val ASumB: Set[Either[Int, Boolean]] = A.map(Left.apply) ++ B.map(Right.apply)
  println(ASumB)


  // Exercise. Implement `makeTransfer` using `for` comprehensions and the methods provided in `UserService`.

  type UserId = String
  type Amount = BigDecimal

  trait UserService {
    def validateUserName(name: String): Either[ErrorMessages, Unit]

    def findUserId(name: String): Either[ErrorMessages, UserId]

    def validateAmount(amount: Amount): Either[ErrorMessages, Unit]

    def findBalance(userId: UserId): Either[ErrorMessages, Amount]

    /** Upon success, returns the resulting balance */
    def updateAccount(userId: UserId, previousBalance: Amount, delta: Amount): Either[ErrorMessages, Amount]
  }

  // Upon success, should return the remaining amounts on both accounts as a tuple.
  //Using `for` comprehensions
  def makeTransfer(
                    service: UserService,
                    fromUserWithName: String,
                    toUserWithName: String,
                    amount: Amount
                  ): Either[ErrorMessages, (Amount, Amount)] = {
    // Replace with a proper implementation that uses validateUserName on each name,
    // findUserId to find UserId, validateAmount on the amount, findBalance to find previous
    // balances, and then updateAccount for both userId-s (with a positive and negative
    // amount, respectively):
    println(s"$service, $fromUserWithName, $toUserWithName, $amount")
    import service._

    for {
      isUser1Valid <- service.validateUserName(fromUserWithName)
      isUser2Valid <- service.validateUserName(toUserWithName)
      user1Id <- service.findUserId(fromUserWithName)
      user2Id <- service.findUserId(toUserWithName)
      isValidAmount <- service.validateAmount(amount)
      user1Balance <- service.findBalance(user1Id)
      user2Balance <- service.findBalance(user2Id)
      //      _ <- Either.cond(user1Balance >= amount, (), "Not enough funds") // smt doesn't work here
      newUser1Balance <- service.updateAccount(user1Id, user1Balance, -amount)
      newUser2Balance <- service.updateAccount(user1Id, user2Balance, amount)
    } yield (newUser1Balance, newUser2Balance)
  }

  //Using flatMap and map
  //Seems hard to read when use flatMap
  def makeTransfer2(
                     service: UserService,
                     fromUserWithName: String,
                     toUserWithName: String,
                     amount: Amount
                   ): Either[ErrorMessages, (Amount, Amount)] = {
    // Replace with a proper implementation that uses validateUserName on each name,
    // findUserId to find UserId, validateAmount on the amount, findBalance to find previous
    // balances, and then updateAccount for both userId-s (with a positive and negative
    // amount, respectively):
    println(s"$service, $fromUserWithName, $toUserWithName, $amount")
    import service._

    service.validateUserName(fromUserWithName).flatMap { _ =>
      service.validateUserName(toUserWithName).flatMap { _ =>
        service.findUserId(fromUserWithName).flatMap { user1Id =>
          service.findUserId(toUserWithName).flatMap { user2Id =>
            service.validateAmount(amount).flatMap { _ =>
              service.findBalance(user1Id).flatMap { user1Balance =>
                service.findBalance(user2Id).flatMap { user2Balance =>
                  service.updateAccount(user1Id, user1Balance, -amount).flatMap { newUser1Balance =>
                    service.updateAccount(user2Id, user2Balance, amount).map {
                      newUser2Balance => (newUser1Balance, newUser2Balance)
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  //Try
  //Success(value) - Success + value
  //Failure(error) - Failure + exception
  def parseInt1(x: String): Try[Int] = Try(x.toInt)

  parseInt1("asdf") match {
    case Success(value) => println(value)
    case Failure(error) => println(error)
  }
}
