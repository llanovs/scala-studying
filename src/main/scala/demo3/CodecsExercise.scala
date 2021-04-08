package demo3

object CodecsExercise extends App {

  //ADT
  sealed trait Json {
    def /(key: String): JsonResult = this match {
      case JsonObject(value) => JsonResult(value.get(key))
      case _ => JsonResult(None)
    }
  }

  final case object JsonNull extends Json

  final case class JsonString(value: String) extends Json

  final case class JsonInt(value: Int) extends Json

  final case class JsonArray(value: List[Json]) extends Json

  final case class JsonObject(value: Map[String, Json]) extends Json

  final case class JsonResult(v: Option[Json]){

    def /(key: String): JsonResult = ???

    def as[A: Decoder]: Option[A] = {
      v.flatMap(Decoder[A].fromJson)
    }
  }

  // Encoder
  trait Encoder[A] { self =>
    def toJson(a: A): Json

    def contrmap[B](f: B => A): Encoder[B] = (a: B) => self.toJson(f(a))
  }

  object Encoder {
    def apply[A: Encoder]: Encoder[A] = implicitly[Encoder[A]]
  }

  implicit class EncoderOps[A: Encoder](a: A) {
    def toJson: Json = Encoder[A].toJson(a)
  }

  // Decoder
  trait Decoder[A] { self =>
    def fromJson(json: Json): Option[A]

    def map[B](f: A => B): Decoder[B] = (json: Json) => self.fromJson(json).map(f)
  }

  object Decoder {
    def apply[A: Decoder]: Decoder[A] = implicitly[Decoder[A]]
  }

  implicit class DecoderOps(json: Json) {
    def as[A: Decoder]: Option[A] = Decoder[A].fromJson(json)
  }

  // Exercise 1. Implement Encoder and Decoder for Int.
  implicit val IntEncoder: Encoder[Int] = (a: Int) => JsonInt(a)

  implicit val IntDecoder: Decoder[Int] = {
    case JsonInt(value) => Some(value)
    case JsonNull => None
  }

  100.toJson
  JsonNull.as[Int]

  // Exercise 2. Implement Encoder and Decoder for String.
  implicit val StringEncoder: Encoder[String] = (a: String) => JsonString(a)
  implicit val StringDecoder: Decoder[String] = {
    case JsonString(value) => Some(value)
    case _ => None
  }

  "Example".toJson
  JsonNull.as[String]


  final case class Person(name: String, age: Int)

  // Exercise 3. Implement Encoder and Decoder for Person.
  implicit val PersonEncoder: Encoder[Person] =
    (person: Person) => JsonObject(
      Map (
        "name" -> person.name.toJson,
        "age" -> person.age.toJson
      )
    )


//  Variant 1
  implicit val PersonDecoder: Decoder[Person] = {
    case JsonObject(jsonValue) =>
      Some(
      Person(
        jsonValue.get("name").flatMap(_.as[String]).get,
        jsonValue.get("age").flatMap(_.as[Int]).get)
    )
    case JsonNull => None
  }

//  Variant 2
//  implicit val PersonDecoder1: Decoder[Person] = {
//    value => for {
//      name <- (value / "name").as[String]
//      age <- (value / "age").as[Int]
//    } yield Person(name, age)
//  }

  Person("Ivan", 25).toJson
  JsonNull.as[Person]

  final case class EntityId(id: String) extends AnyVal

  // Exercise 5. Implement Encoder and Decoder for EntityId with any content.
  implicit val idEncoder: Encoder[EntityId] = Encoder[String].contrmap[EntityId](_.id)
                                        // OR  _.id.toJson

  implicit val idDecoder: Decoder[EntityId] = Decoder[String].map(EntityId)
                                        //  OR  (t: Json) => t.as[String].map(EntityId)

  // Exercise 6. Describe Functor
  // 1. Typeclass itself: `trait Functor`
  sealed trait Functor[F[_]]{
    def fmap[A, B](fa: F[A])(f: A => B) : F[B]
  }

  // 2. Typeclass Summoner: `object Functor`
  object Functor{
    def apply[F[_]](implicit a: Functor[F]): Functor[F] = a
  }

  // Exercise 8. Describe Contravariant
  // 1. Typeclass itself: `trait Contravariant`
  sealed trait Contravariant[F[_]]{
    def contrmap[A, B](fa: F[A])(f: B => A): F[B]
  }

  // 2. Typeclass Summoner: `object Contravariant`
  object Contravariant {
    def apply[F[_]](implicit a: Contravariant[F]): Contravariant[F] = a
  }

  // Functions Example
  val foo1: String => Int = _.length
  val foo2: Boolean => String = if (_) "100" else "1"

  val fv : Functor[Boolean => *] = new Functor[Boolean => *]{
    override def fmap[A, B](fa: Boolean => A)(f: A => B): Boolean => B = t => f(fa(t))
  }

  val cf : Contravariant[* => Int] = new Contravariant[* => Int] {
    override def contrmap[A, B](fa: A => Int)(f: B => A): B => Int = t => fa(f(t))
  }


  //Additional tasks

  // Exercise 1. Implement Encoder and Decoder for List with any content.
  // implicit def listEncoder[A: Encoder]: Encoder[List[A]] = ???
  // implicit def listDecoder[A: Decoder]: Decoder[List[A]] = ???

  // Exercise 2. Implement
  // 3. Typeclass Ops: `implicit class FunctorOps`
  // 3. Typeclass Ops: `implicit class ContravariantOps`

  // Exercise 3. Implement Functor for decoder: `implicit val decoderFunctor`
  //  implicit val decoderFunctor = ???

  // Exercise 4. Implement Functor and Contravariant for functions:
  // implicit def functionFunctor
  // implicit def functionContravariant

  // val foo3: Boolean => Int = functionFunctor.fmap(foo2)(foo1)
  // val foo4: Boolean => Int = functionContravariant.contramap(foo1)(foo2)

  // Exercise 5. Implement Contravariant for encoder: `implicit val encoderContravariant`
}
