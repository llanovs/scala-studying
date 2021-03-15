package demo2

import scala.util.matching.Regex

object RegularExpressions extends App{

  //Regex
  //Use scala.util.matching
  //Scala inherits its regular expression syntax from Java
  //Create a String and call the r() method on it.
  //Scala implicitly converts the String to a RichString and
  //invokes that method to get an instance of Regex.
  //To find a first match of the regular expression,
  //simply call the findFirstIn() method.
  //If instead of finding only the first occurrence we would
  //like to find all occurrences of the matching word,
  //we can use the findAllIn() method and in case there are
  //multiple Scala words available in the target string,
  //this will return a collection of all matching words.
  //Return an Option

  val pattern = "Scala".r
  val str = "Scala is Scalable and cool"
  var result = (pattern findFirstIn str).getOrElse("Not found")
  println(result)

  //Use Regex constructor instead or r() method to create a pattern.
  //Use mkString() method to concatenate the resulting list
  val pattern2 = new Regex("(S|s)c")
  println((pattern2 findAllIn str).mkString(","))

  //If you would like to replace matching text, can be used replaceFirstIn()
  //to replace the first match or replaceAllIn() to replace all occurrences.
  val pattern3 = new Regex("(S|s)cala")
  println((pattern2 replaceFirstIn(str, "Java")))
  
}
