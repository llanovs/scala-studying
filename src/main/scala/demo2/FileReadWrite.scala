package demo2

import java.io.{File, PrintWriter}

import scala.io.Source

object FileReadWrite extends App{
  //write to file, rewrite if the file is already exist
  val fileObj = new File("1.txt")
  val writer = new PrintWriter(fileObj)
  writer.write("Hello, world!")
  writer.close()

  //read from file
  val source = Source.fromFile(fileObj)
  while (source.hasNext){
    print(source.next())
  }
  source.close()
  println()

  //reading Each Line
  val filename = "ScalaFile.txt"
  val fileSource = Source.fromFile(filename)
  for(line <- fileSource.getLines){
    println(line)
  }
  fileSource.close()
  writer.write("Hello, world!")
  writer.close()
}
