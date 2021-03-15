package demo2

import scala.collection.{BitSet, SortedSet}
import scala.collection.immutable.{HashMap, HashSet, ListMap, ListSet, Queue}

object LearnCollenctions extends App {

  //Collections in Scala can be mutable or immutable
  //Note: a - abstract class, t - trait(interface)

  //Scala.collection.immutable package contains all immutable collections

  //Set(Set(t), SortedSet(t) <-- TreeSet(a), HashSet(a), BitSet(a), ListSet(a))
  //Seq(Seq(t), IndexedSeq(t) <-- Vector(a), NumericRange(a), String(a), Range(a),
  //            LinearSeq(t) <-- List(a), Stream(a), Queue(a), Stack(a))
  //Map(Map(t), SourcedMap(t) <-- TreeMap(a), HashMap(a), ListMap(a)

  //Scala.collection.mutable package contains all the mutable collections
  //so you must import scala.collection.mutable package in your code


  //SET

  //SET - store unique elements in the set
  //by default immutable
  val set = Set()
  val alphabet = Set("A", "B", "C", "D", "E")
  val games = Set("Cricket", "Football", "Hockey", "Golf")

  //adding new element doesn't allow if immutable set
  println(games)

  println(s"Head: ${games.head}")
  println(s"Tail: ${games.tail}")
  println(s"Is Empty: ${games.isEmpty}")
  println(s"Is contains Football: ${games.contains("Football")}")
  println(s"Is contains Chess: ${games.contains("Chess")}")

  //merge 2 sets
  val mergeSet = games ++ alphabet
  println(s"Elements in mergeSet: ${mergeSet}")

  //mutable set
  //Add/remove elements
  val games2 = scala.collection.mutable.Set("Cricket", "Football")
  println(games2)
  games2.add("Chess")
  println(games2)
  games2.remove("Football")
  println(games2)

  //Iterating Set Elements using for loop
  for (game <- games2) {
    println(s"I love play $game")
  }

  //Iterating Set Elements using foreach loop
  games.foreach((element: String) => println(s"I love play $element"))

  //SORTEDSET
  println()
  var numbers: SortedSet[Int] = SortedSet(5, 8, 1, 2, 9, 6, 4, 7, 2)
  numbers.foreach((element: Int) => print(element + " "))

  //HASHSET
  println()
  var hashset = HashSet(4, 2, 8, 0, 6, 3, 45)
  hashset.foreach((element: Int) => print(element + " "))

  //BITSET

  //Bitsets are sets of non-negative integers which are represented
  //as variable-size arrays of bits packed into 64-bit words.
  //The memory footprint of a bitset is determined by the largest
  //number stored in it.
  println()
  val numbers3 = BitSet(1, 5, 8, 6, 9, 0)
  numbers3.foreach((element: Int) => print(element + " "))
  //Scala ListSet
  //ListSet class implements immutable sets using a list-based data structure.
  //Elements are stored internally in reversed insertion order,
  //which means the newest element is at the head of the list
  //This collection is suitable only for a small number of elements.
  println()
  var listSet = ListSet(4, 2, 8, 0, 6, 3, 45)
  listSet.foreach((element: Int) => print(element + " "))


  //SEQ

  //Seq is a trait which represents indexed sequences that are
  //guaranteed immutable. You can access elements by using their indexes.
  //It maintains insertion order of elements.
  println()
  var seq: Seq[Int] = Seq(52, 85, 1, 8, 3, 2, 7)
  seq.foreach((element: Int) => print(element + " "))
  println(s"\nAccessing element by using index ${seq(2)}")
  println("Ends with (2,7): " + seq.endsWith(Seq(2, 7)))
  println("last index of 3 : " + seq.lastIndexOf(3))
  println("Reverse order of sequence: " + seq.reverse)

  //VECTOR

  //Vector - immutable data structure
  //It provides random access of elements.
  var vector: Vector[Int] = Vector(5, 8, 3, 6, 9, 4)
  var vector2 = Vector(5, 2, 6, 3)
  var vector3 = Vector.empty

  println(vector)
  //The same actions like in other collections

  //LIST

  //List is used to store ordered elements.
  //It is a class for immutable linked lists.
  //This class is good for last-in-first-out (LIFO),
  //stack-like access patterns.
  //It maintains order of elements and can contain duplicates elements
  var list = List(1, 8, 5, 6, 9, 58, 23, 15, 4)
  var list2: List[Int] = List(1, 8, 5, 6, 9, 58, 23, 15, 4)
  println(list)
  //The same actions like in other collections

  //QUEUE

  //Implements a data structure that allows inserting and
  //retrieving elements in a first-in-first-out (FIFO) manner.
  //Queue is implemented as a pair of lists
  //One is used to insert the elements and second to contain deleted
  //elements. Elements are added to the first list and removed from
  //the second list.
  var queue = Queue(1, 5, 6, 2, 3, 9, 5, 2, 5)
  var queue2: Queue[Int] = Queue(1, 5, 6, 2, 3, 9, 5, 2, 5)
  println(queue)
  print(s"First element in the queue: ${queue.front}")
  print(s"\nElement added in the queue: ${queue.enqueue(12)}")
  print(s"\nFirst Element deleted in the queue: ${queue.dequeue}")
  //The same actions like in other collections

  //STREAM

  //Stream is a lazy list.
  //It evaluates elements only when they are required.
  println()
  val stream = 100 #:: 200 #:: 85 #:: Stream.empty
  println(stream)
  //you can see that second element is not evaluated marked as ?
  println(s"Head of stream is ${stream.head}")
  println(s"Tail of stream is ${stream.tail}")
  //The same actions like in other collections

  //MAP

  //You can add and remove new elements in maps.
  //Scala provides you lots of predefined method.
  var map = Map("A" -> "Apple", "B" -> "Ball")
  println(map("A"))
  var newMap = map + ("C" -> "Cat")
  println(newMap)
  var removeElement = newMap - ("B")
  println(removeElement)

  //The same actions like in other collections

  //HASHMAP
  //It use hash code to store elements and return a map.
  var hashMap = HashMap("A" -> "Apple", "B" -> "Ball", "C" -> "Cat")
  println(hashMap)
  //Iterating elements
  hashMap.foreach {
    case (key, value) => println(key + " -> " + value)
  }
  println(s"Key: C, Value: ${hashMap("C")}")


  //LISTMAP
  //Immutable maps by using a list-based data structure
  //This collection is suitable for small elements
  var listMap = ListMap("Rice" -> "100", "Wheat" -> "50", "Gram" -> "500")

}
