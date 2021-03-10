package demo

object Function2 {

  def main(args: Array[String]): Unit = {

    //Higher order functions
    var result = math(12, 20.5, (x, y) => x + y);
    println(result);

    var result1 = math(12, 20.5, (x, y) => x * y);
    println(result1);

    var result2 = math(12, 20.5, (x, y) => x min y);
    println(result2);

    var result3 = math(12, 20.5, 34.5, (x, y) => x + y);
    println(result3);

    //for easy writing use can use _ instead of param
    //some unknown param add another unknown param
    var result4 = math(12, 20.5, 34.5, _ + _);
    println(result4);
  }

  def math(x: Double, y: Double,
           f: (Double, Double) => Double): Double = f(x, y);

  def math(x: Double, y: Double, z: Double,
           f: (Double, Double) => Double): Double = f(f(x, y), z);


}
