package demo

class Swan extends  Animal with Bird {
  override def getName: String = "Swan"

  override def getSpecieOfBird: String = "Ducks"
}
