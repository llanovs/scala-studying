package demo

class Square(var width: Double)
              extends Shape {

  override def area: Double = Math.pow(width, 2)
}
