package demo

object Maps {

  //immutable
  val user: Map[Int, String] = Map(801 -> "Anna",
    802 -> "Tom", 803 -> "John", 804 ->  "Andrew");

  val newUser: Map[Int, String] = Map(800 -> "Anna",
    805 -> "Tom", 813 -> "John", 804 ->  "Andrew");


  def main(args: Array[String]): Unit = {
    println(user)
    println(user(802))
    println(user.keys)
    println(user.values)
    println(user.isEmpty)
    println(user.size)
    println(user.contains(801))
    println(user.contains(1))
    println(user ++ newUser)

    user.keys.foreach{
      key => println("Key: " + key + ", value: " + user(key))
    }
  }

}
