package demo

object Conditions {
  def main(args: Array[String]): Unit = {
    //if-else statement
    val x = 20
    var res  = ""

    if(x == 20){
      res = "x = 20"
    } else {
      res = "x != 20"
    }

    println(res)

    //if-else expression
    var res2 = if(x == 20) "x = 20" else "x != 20"
    println(res2)


    //match expressions (like switch)
    var age = 20

    age match{
      case 20 => println(age)
      case 18 => println(age)
      case 30 => println(age)
      case _ => println("Default")
    }

    //match expressions (like switch)
    val res3 = age match{
      case 20 => age
      case 210 => age
      case _ => 0
    }

    println(res3)

    //match expressions (like switch) multiple cases
    val res4 = age match{
      case 1 | 3| 7| 12| 20 => age
      case 210 => 0
      case _ => 0
    }
    println(res4)
  }


}
