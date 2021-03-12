package demo

object Classes {

  class User(private var name: String,
             var age: Int){

    //Auxiliary constructor
    //use key word this with for example no arguments
    def this(){
      this("NoName", 0)
    }

    //Auxiliary constructor
    //use key word this with for example 1 argument
    def this(name: String){
      this(name, 0)
    }

    def getName: String = name;

    def setName(newName: String): Unit = name = newName;

    def print(): Unit ={
      println(s"Name: $name, age: $age")
    }
  };

  def main(args: Array[String]): Unit = {

    //Object - it's a singleton class so you cannot create
    //an instance of object like object Classes etc

    //Class - it's a template to create an object, so you can
    //create an instance of class
    //if variables in the class constructor are
    //var - they can be changed
    //val - it will be const

    //var... you have getter and setter
    //val...you have getter
    //default...you don't have getter and setter

    var user = new User("Anna", 23 );
    println("Name: "+ user.getName)
    println("Age: "+ user.age)

    user.setName("Max")
    user.age = 24;
    println("Name: "+ user.getName)
    println("Age: "+ user.age)

    //Auxiliary constructors
    //it's alternative constructors that must
    //have a different number of arguments and
    //always call a primary constructor
    var user1 = new User();
    var user2 = new User("Anna" );
    user.print()
    user1.print()
    user2.print()


    //Inheritance


    //


  }
}
