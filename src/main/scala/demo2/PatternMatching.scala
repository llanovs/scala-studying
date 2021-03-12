package demo2

object PatternMatching extends App{

  //switch expression
  val dayOfweek = 4

  val day = dayOfweek match {
    case 1 => "Monday"
    case 2 => "Tuesday"
    case 3 => "Wednesday"
    case 4 => "Thursday"
    case 5 => "Friday"
    case 6 => "Saturday"
    case 7 => "Sunday"
    case _ => s"Some wrong day: $dayOfweek"
  }

  println(day)

  //also use in case classes
  case class Person(name: String, age: Int);

  val bob = Person("Bob", 32)
  val personGreeting = bob match {
    case Person(n, a) => s"Hi, my name is $n and I'm $a years old"
    case _ => "Something else"
  }

  println(personGreeting)

  //deconstructing tuples
  val tuple = ("Bon Jovi", "Rock")
  val bandDescription  = tuple match{
    case (band, genre) => s"$band belongs to the genre $genre"
    case _ => "Something else"
  }

  println(bandDescription)

  //decomposing lists
  val list = List(1,2,3)
  val  listDescription = list match{
    case List(_,2,_) => "List containing 2 on the second position"
    case _ => "Unknown list"
  }

  println(listDescription)

  //if doesn't match anything it throw MatchError
  try {
    val r = List(1,1,3,4) match{
      case List(_,2,_) => "List containing 2 on the second position"
    }
    println(r)
  }catch{
    case e: Exception => println(s"Error: $e")
  }
}
