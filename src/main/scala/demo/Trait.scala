package demo

object Trait {

  def main(args: Array[String]): Unit = {
      var swan = new Swan;
      println(s"Animal: ${swan.getName}, " +
              s"Specie of bird: ${swan.getSpecieOfBird}")
  }
}
