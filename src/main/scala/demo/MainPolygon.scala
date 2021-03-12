package demo

object MainPolygon {

  def main(args: Array[String]): Unit = {
      var polygon = new Polygon;
      printArea(polygon)

      var rectangle = new Rectangle(55.2, 10.4);
      printArea(rectangle)

      var triangle = new Triangle(12, 43);
      printArea(triangle)
  }

  def printArea(p: Polygon): Unit ={
    println(s"Area of ${p.name} is ${p.area}");
  }
}
