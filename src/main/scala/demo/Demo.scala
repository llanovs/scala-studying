package demo

object  Demo {

  def main(args: Array[String]): Unit = {
    val name = "Mark";
    val age =  18;

    //1 variant
    println(name + " is " + age + " years old")

    //2 variant
    println(s"$name is $age years old")

    //3 variant
    println(f"$name%s is $age%d years old")

    //Print Enter
    println(s"Hello \n world!")

    //Don't print Enter
    println(raw"Hello \n world!")
  }
}
