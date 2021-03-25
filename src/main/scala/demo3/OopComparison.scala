package demo3

import demo3.OopComparison.InstancesTask.Player
import demo3.OopComparison.Summoner.Jsonable

object OopComparison extends App {

  //Type class is a trait with type parameter

    final case class Json(s: String) // my very basic json class

    trait Jsonable[T] { // <- the typeclass itself: a trait with one type parameter
      //           ^
      //   the type parameter
      def toJson(entity: T): Json // it may have many methods but usually one
    }

    // a typeclass describes an interface which can be implemented for different types

    // my imaginary method changed its signature a bit
    def printBeautifully[A](x: A)(implicit jsonable: Jsonable[A]): Unit = {
      println(jsonable.toJson(x))
    }

    // my entity does not implement anything and can be implemented with no idea Jsonable exists
    final case class User(name: String)

    // here goes the implementation
    // we can define it not touching User or Jsonable or anything
    implicit val userJsonable: Jsonable[User] = new Jsonable[User] {
      def toJson(user: User): Json = Json(s"{name: ${user.name}}")
    }

    // the usage is the same
    printBeautifully(User("Oleg"))

    // Task
    object InstancesTask {
      final case class Player(id: Int, login: String)

      implicit val playerJsonable: Jsonable[Player] = new Jsonable[Player] {
        override def toJson(player: Player): Json =
          Json(s"{playerId: ${player.id}, login: ${player.login}}")
      }

      implicit val intJsonable: Jsonable[Int] = new Jsonable[Int] {
        override def toJson(value: Int): Json = Json(s"{value: $value}")
      }

      implicit val optionIntJsonable: Jsonable[Option[Int]] = new Jsonable[Option[Int]] {
        override def toJson(entity: Option[Int]): Json = entity match {
          case None => Json(s"{error: value not found}")
          case Some(value) => Json(s"{value: $value}")
        }
      }
    }

  printBeautifully(Player(1, "Oleg"))


  //Task 2
  object SingleAbstractMethod {
    // they are the same, choose any style you like

    implicit val was: Jsonable[User] = new Jsonable[User] {
      def toJson(user: User): Json = Json(s"{name: ${user.name}")
    }

    // the same in the previous row
    implicit val now: Jsonable[User] = user => Json(s"{name: ${user.name}")

    final case class Player(id: Int, login: String)

    implicit val playerJsonable: Jsonable[Player] =
      player => Json(s"{playerId: ${player.id}, login: ${player.login}}")

    implicit val intJsonable: Jsonable[Int] = value => Json(s"{value: $value}")

    implicit val optionIntJsonable: Jsonable[Option[Int]] = {
      case None => Json(s"{error: value not found}")
      case Some(value) => Json(s"{value: $value}")
    }
  }

  object ContextBound {
    def printBeautifullyOld[A](x: A)(implicit jsonable: Jsonable[A]): Unit = {
      println(jsonable.toJson(x))
    }

    //using method implicitly to get implicit value
    // and type like implicit [A: Jsonable]
    def printBeautifully[A: Jsonable](x: A): Unit = {
      val jsonable = implicitly[Jsonable[A]]
      println(jsonable.toJson(x))
    }
  }

  //simplify previous example
  object Summoner {
    object Jsonable { // but it gets a companion object

      // with nice summon method (could have any name, apply for eg)
      def apply[F](implicit instance: Jsonable[F]): Jsonable[F] = instance
    }

    // so now we can change
    def printBeautifullyOld[A: Jsonable](x: A): Unit = {
      val jsonable = implicitly[Jsonable[A]]
      println(jsonable.toJson(x))
    }

    // to
    def printBeautifully[A: Jsonable](x: A): Unit = {
      println(Jsonable[A].toJson(x))
    }
  }

  //make syntax easier for using chain easier then
  object Syntax {

    object JsonableSyntax {

      implicit class JsonableOps[A](x: A) {
        def toJson(implicit j: Jsonable[A]): Json = j.toJson(x)
      }
    }

    // so now we can change
    def printBeautifullyOld[A: Jsonable](x: A): Unit = {
      println(Jsonable[A].toJson(x))
    }

    // to
    import JsonableSyntax._
    def printBeautifully[A: Jsonable](x: A): Unit = {
      println(x.toJson)
    }
  }

  object Result {

    // --- json library (provides the typeclass) ---
    trait Jsonable[T] {
      def toJson(entity: T): Json
    }

    object Jsonable {
      def apply[F](implicit instance: Jsonable[F]): Jsonable[F] = instance
    }

    object JsonableSyntax {

      implicit class JsonableOps[A](x: A) {
        def toJson(implicit j: Jsonable[A]): Json = {
          j.toJson(x)
        }
      }
    }

    // --- library which makes use of json (for example some http library) ---
    import JsonableSyntax._
    def printBeautifully[A: Jsonable](x: A): Unit = {
      println(x.toJson)
    }

    // --- domain library of your project ---
    final case class User(name: String)

    // --- domain utils library ---
    implicit val JsonableUser: Jsonable[User] = user => Json(s"{name: ${user.name}") // good luck choosing name

    // --- you ---
    printBeautifully(User("Oleg"))
  }
}
