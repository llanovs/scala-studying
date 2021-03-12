package demo

object OptionsExamples {

  val list = List(1, 2, 3)
  val map = Map(1 -> "Tom", 2 -> "Max", 3 -> "John")
  val opt : Option[Int] = Some(5);
  val opt1 : Option[Int] = None;

  def main(args: Array[String]): Unit = {
    println(list.find(_ > 6)) //None
    println(list.find(_ > 2)) //Some
    println(list.find(_ > 2).get) //Value

    println(map.get(2)) //Some
    println(map(2)) //Value
    println(map.get(4)) //None
    println(map.get(4).getOrElse("No name found"))
    println(map.getOrElse(4, "No name found"))

    println(opt)
    println(opt1.isEmpty)
    println(opt.isEmpty)
  }
}
