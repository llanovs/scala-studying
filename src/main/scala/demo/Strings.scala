package demo

object Strings {

  var message: String = "Hello, "
  var name: String = "Anna"
  var number1 = 75
  var number2 = 12.555

  def main(args: Array[String]): Unit = {
    println("Name length: "+ name.length)
    println(message.concat(name))
    println(name.toLowerCase)
    println(name.toUpperCase)
    println("Name equals: " + (name.compareTo("Anna") == 0))
    println("Name equals: " + (name.compareTo("Irina") == 0))

    //string format
    var result = printf("%d -- %f -- %s \n", number1, number2, name)

    println("%d -- %f -- %s".format(number1, number2, name ))
  }
}
