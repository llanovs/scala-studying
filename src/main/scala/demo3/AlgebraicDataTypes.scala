package demo3

object AlgebraicDataTypes extends App {

  // ALGEBRAIC DATA TYPES

  // Algebraic Data Types or ADTs is a commonly used way of structuring data, used in many programming
  // languages (so this is not something unique to Scala).
  //
  // While the definition may sound scientific and complex, in reality we have already been using this
  // concept in `basics` package. For example, see `sealed trait Shape` in `ClassesAndTraits`. We will now
  // look into the concept and its use cases in more detail.

  // ADTs are widely used in Scala for multiple reasons:
  // - to ensure it is hard or even impossible to represent invalid data;
  // - because pattern matching and ADTs play nicely together.

  // Two common classes of ADTs are:
  // 1. product types: case classes and tuples;
  // 2. sum types: sealed traits and abstract classes.

  // PRODUCT TYPES

  // A product type allows to combine multiple values into one. Canonical Scala examples of product types are
  // case classes and tuples. See `Basics` and `ClassesAndTraits` for their introduction.

  // A product type is called like that because one can calculate how many different values it can possibly
  // have by multiplying the number of such possibilities for the types it combines. The resulting number
  // is called the arity of the product type.

  // VALUE CLASSES

  // Value classes are a mechanism in Scala to avoid allocating runtime objects, while still providing
  // additional type safety. Runtime objects are not allocated in most cases, but there are notable
  // exceptions, see the following link for more details:
  // https://docs.scala-lang.org/overviews/core/value-classes.html

  // SMART CONSTRUCTORS

  // Smart constructor is a pattern, which allows creating only valid instances of a class.

  // Exercise. Create a smart constructor for `GameLevel` that only permits levels from 1 to 80.
  final case class GameLevel private(value: Int) extends AnyVal

  object GameLevel {
    def create(value: Int): Option[GameLevel] = {
      if (value < 1 || value > 80) None
      else Some(GameLevel(value))
    }
  }

  // Exercise. Implement the smart constructor for `Time` that only permits values from 00:00 to 23:59 and
  // returns "Invalid hour value" or "Invalid minute value" strings in `Left` when appropriate.
  sealed abstract case class Time private(hour: Int, minute: Int)

  object Time {
    def create(hour: Int, minute: Int): Either[String, Time] = {
      if (hour < 0 || hour > 23) Left("Invalid hour value")
      else if (minute < 0 || minute > 59) Left("Invalid minute value")
      else Right(new Time(hour, minute) {})
    }
  }

  // SUM TYPES

  // A sum type is an enumerated type. To define it one needs to enumerate all its possible variants.
  final case class AccountNumber(value: String) extends AnyVal

  final case class CardNumber(value: String) extends AnyVal

  final case class ValidityDate(month: Int, year: Int)

  sealed trait PaymentMethod

  object PaymentMethod {

    final case class BankAccount(accountNumber: AccountNumber) extends PaymentMethod

    final case class CreditCard(cardNumber: CardNumber, validityDate: ValidityDate) extends PaymentMethod

    final case object Cash extends PaymentMethod

  }

  import PaymentMethod._

  final case class PaymentStatus(value: String) extends AnyVal

  trait BankAccountService {
    def processPayment(amount: BigDecimal, accountNumber: AccountNumber): PaymentStatus
  }

  trait CreditCardService {
    def processPayment(amount: BigDecimal, cardNumber: CreditCard): PaymentStatus
  }

  trait CashService {
    def processPayment(amount: BigDecimal): PaymentStatus
  }

  // Exercise. Implement `PaymentService.processPayment` using pattern matching and ADTs.
  class PaymentService(
                        bankAccountService: BankAccountService,
                        creditCardService: CreditCardService,
                        cashService: CashService,
                      ) {
    def processPayment(amount: BigDecimal, method: PaymentMethod): PaymentStatus = {
      ???
//      method.asInstanceOf match {
//      case BankAccount => bankAccountService.processPayment(amount, _)
//      case CreditCard => creditCardService.processPayment(amount, _)
//      case Cash => cashService.processPayment(amount)
//      case _ =>  cashService.processPayment(amount)
//    }
    }
  }

  // Let's compare that to `NaivePaymentService.processPayment` implementation, which does not use ADTs, but
  // provides roughly the same features as `PaymentService`.
  // Question. What are disadvantages of `NaivePaymentService`? Are there any advantages?
  trait NaivePaymentService { // Obviously a bad example!
    def processPayment(
                        amount: BigDecimal,
                        bankAccountNumber: Option[String],
                        validCreditCardNumber: Option[String],
                        isCash: Boolean,
                      ): String = ???
  }


  // Exercise. Define an Algebraic Data Type `Car`, which has a manufacturer, a model, a production year,
  // and a license plate number (can contain from 3 to 8 upper case letters and numbers). Use value classes
  // and smart constructors as appropriate.
  type Car = Nothing


}
