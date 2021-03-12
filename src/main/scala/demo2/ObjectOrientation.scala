package demo2

import scala.Byte.MaxValue

object ObjectOrientation extends App{

  class Animal{
    val color = "Black";

    def eat() = "I'm eating"
  }

  //class definition specified the
  //class constructor that use when object instantiated
  //constructor arguments ARE NOT fields
  class Dog(name: String, var age:Int) extends Animal;

  val animal = new Animal;
  animal.eat();

  val dog = new Dog("Lassi", 3);
  dog.age = 8
  dog.eat()

  //subtype polymorphism
  val someAnimal: Animal = new Dog("Harry", 2)
  someAnimal.eat()

  //abstract class
  abstract class WalkingAnimal{
    val hasLegs = true
    //all fields by default are public

    def walk(): Unit
  }

  //interface => Trait
  trait Carnivore{
    def eat(animal: Animal): Unit
  }

  //single class inheritance
  //multi-trait mixing
  class Crocodile extends Animal with Carnivore{
    override def eat(animal: Animal): Unit = println("Crocodile is eating")
  }

  //method naming
  val crocodile = new Crocodile;
  crocodile.eat(dog)
  crocodile eat dog //infix notation use when 1 argument

  trait Philosopher{
    def ?!(thought: String): Unit //valid name
  }

  //anonymous classes
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I can eat")
  }

   //singleton object
  object MyObject{
     val value = 55;

     def foo(): Int = 5

     def apply(x: Int): Int = value + x
   }

  println(MyObject.apply(3))
  println(MyObject(3))

  println(MyObject.value)

  //class(trait) Animal and object Animal are companians
  //companion object
  object Animal{
    //can access each other private fields
    val canLiveIndefinitely = false
   }

  val animal2 = Animal.canLiveIndefinitely //like static fields

  //case classes
  //lightweight data structures with some boilerplate
  //sensible Equals and hashcode
  //serialization
  //companion with apply
  //pattern matching
  case class Person(name: String, age:Int)

  //may be constructed without new
  //Person.apply("Bob", 23)
  val bob = Person("Bob", 23)

  //exception
  try{
    val x: String = null
    x.length
  }catch{
    case e: Exception => "some error message"
  } finally {
    //execute at the end
  }

  //generics
  abstract class MyList[T]{
    def head: T
    def tail: MyList[T]
  }

  //use a generic with a concrete type
  val list: List[Int] = List(1,2,3)
  val first = list.head
  val rest = list.tail
}
