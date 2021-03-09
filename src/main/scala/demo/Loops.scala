package demo

object Loops {
  def main(args: Array[String]): Unit = {
    //While Loop
    var x = 0
    println("While loop");
    while (x < 10){
      println(s"x = $x");
      x+=1;
    }

    //Do-while Loop
    println("\nDo-While loop");
    x = 0
    do{
      println(s"x = $x");
      x+=1;
    }while (x < 10)

    //For Loop
    println("\nFor loop");
    for (i <- 0 to 9){
      println(s"x = $i");
    }

    //For Loop
    println("\nFor loop");
    for (i <- 0.to(9)){
      println(s"x = $i");
    }

    //For until Loop
    println("\nFor until loop");
    for (i <- 0.until(10)){
      println(s"x = $i");
    }

    //For until Loop
    println("\nFor until loop2");
    for (i <- 0 until 10){
      println(s"x = $i");
    }

    //For until Loop
    println("\nFor loop multiple ranges");
    for (i <- 0 to 9; j <- 1 to 3){
      println(s"x = $i, j = $j");
    }

    //For Loop like iterator of list
    println("\nFor loop iterate list");
    var lst = List(1,2,3,4,5,12,34,5)
    for (i <- lst){
      println(s"x = $i");
    }

    //For Loop with conditions for filtering
    println("\nFor loop with conditions for filtering");
    var lst2 = List(1,342,3,4,25,12,34,5)
    for (i <- lst2; if i < 6){
      println(s"i = $i");
    }


    //For Loop with conditions for filtering like expresion
    println("\nFor loop expresion with conditions for filtering");
    var res = for {i <- lst2; if i < 6}yield{
     i*i
    }
    println(s"result = $res");
  }
}
