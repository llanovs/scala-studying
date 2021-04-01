package demo3

import com.sun.org.apache.xml.internal.security.algorithms.JCEMapper.Algorithm

object HigherKindedTypes extends App {
  /* 1. Higher Order Functions

This lecture will contain a bit of theory to make it easier to understand how some more advanced type
classes can be used in particular and how we can work with types in System Fω̲ type system (with types
dependent on other types) in general.

We'll start with refreshing of some functions theory we had previously.

Currying – conversion of function with multiple arguments into a sequence of functions that each take just
one argument.
So function of 2 arguments can be converted into a function with just one first argument which returns other
function which takes second argument and returns result.

Example:
 */
  def multiArgFunction(a: String, b: Int): Long          = ??? // Type: `(String, Int) => Long`
  def curriedFunction(a: String)(b: Int): Long           = ??? // Type: `String => (Int => Long)`
  def equivalentCurriedFunction1(a: String): Int => Long = ???
  def equivalentCurriedFunction2: String => Int => Long  = ???


  /*
  Exercise 1. "Uncurry" the following function to make it not return other functions,
  but accept more arguments.
   */
  def curriedFunction1: (Long => Boolean) => String => Int = ???
  def curriedFunction(a: Long => Boolean, c: String): Int = ???

  type A = (Long => Boolean) => String => Int
  type B = Long => Boolean => String => Int

  println("A != B")


  /*
 Further in this "Higher Order Functions" block we assume that we are uncurrying all our functions so they
 don't return other functions.

 DEFINITION
 Now let's define order of function as a max order of arguments plus 1.
 Assume that order of "value" is 0 for completeness.

 Examples:
  */

  // Order 0 "function"
  def func0: String = ???
  //def func0: * = ???

  // Typical First-Order Function
  def func1(a: String): Int = ???
  //def func1: * -> * = ???

  // Order 2 Function
  def func2(foo: Int => Boolean): Long = ??? // Type of `func2` is `(Int => Boolean) => Long`
  //def func2: (* -> *) -> * = ???

  // Order 3 Function
  def func3(a: String, bar: (Int => Boolean) => Long): Long = ???
  //def func3: * -> ((* -> *) -> *) -> * = ???

  // Example: Order 2 Function
  def func2a(a: String, bar: Int => (Boolean => Long)): Long = ???
  //def func2: * -> * ->(* -> *) -> * = ???

  // Exercise 2. What is order of the following function?
  def funcX1(a: Int => String => Boolean): Long = ???
  type C = (Int, String) => Boolean // Function 2
  //def func2: (* -> *) -> * = ???

  // Exercise 3. What is order of the following function? = 2
  def funcX2(a: String): (Long => Boolean) => String = ???
  type Q = (String => (Long => Boolean)) => String // Function 2
  //def func2: * -> (* -> *) -> * = ???

  //Algorithm
  def foo(a: String => Int) : (Long => Boolean) => Long = ???

  type G = (String => Int) => (Long => Boolean) => Long
  //def func2: (* -> *) -> (* -> *) -> * = ???

  //function (String => Int) that return (Long => Boolean) => Long
  //function (Long => Boolean) that return Long

  //make carrying
  //((String => Int), (Long => Boolean)) => Long
  //def foo(a: String => Int) : (Long => Boolean) => Long =
  //def foo(a: String => Int, b: Long => Boolean) : Long =

  //as Long doesn't a function so it's 0
  //both of arguments ((String => Int), (Long => Boolean)) // 2
  //doesn't return a function


  // Exercise 4. Write an example of Order 4 function:
  def func4(foo: Int, foo2: ((Int => Boolean) => Boolean) => Long) : Long = ???
  //def func1: (* -> (((* -> *) -> *) -> *)) -> * = ???

  /*
  Types as well as argument names in our signatures are not important to calculate order of function.
  Let's ignore them and see just pure "shape" of function (imagine that we are in non-typed world).

  So to describe "shape" or "kind" of the function we will replace values with stars `*` and functions with
  arrows `->`.

  ┌──────────┬───────────────────────────┐
  │ Function │          "Kind"           │
  ├──────────┼───────────────────────────┤
  │ func0    │ *                         │
  │ func1    │ * -> *                    │
  │ func2    │ (* -> *) -> *             │
  │ func3    │ * -> ((* -> *) -> *) -> * │
  └──────────┴───────────────────────────┘

  Step-by-Step (func3):
  - func3(a: String, bar: (Int => Boolean) => Long): Long
  - func3(a: String)(bar: (Int => Boolean) => Long): Long
  - (String)((Int => Boolean) => Long): Long
  - (String) => ((Int => Boolean) => Long) => Long
  - * -> ((* -> *) -> *) -> *
   */

  // Exercise 5. What are the kinds of `funcX1` and `funcX2`?
   def funcX11(a: Int => String => Boolean): Long = ???
  //def func2: (* -> * -> *) -> * = ???

   def funcX12(a: String): (Long => Boolean) => String = ???
  //def func1: * -> (* -> *) -> * = ???

  /* 2. Higher Kinded Types
  Let's switch from functions to types. Types also can have parameters – other types. For example: `List`,
  `Option`, `Map`.
  Such types which accepts other types are usually called type constructors. Also we can say that type
  `List[A]` depends on type `A`.
  Type constructor itself can't be used as a type for specific value. You can't do the following:

  val list: List = ???

  It will not compile.
  But type constructor can be "invoked" of "filled" with specific types and after that it can be used as a
  type for values.
   */

  // Example. `List` is a type constructor and it's "filled" with a type `Int`.
  // val listOfInts: List[Int] = ???

  /*
    So we treat `List` here as a "function" which can accept other type `Int` as argument, and result of this
    "call" can be written as a `List[Int]` (which is a "value" in terms of functions).


    Now we can draw a parallel between types and functions.

    ┌────────────────────────────────────────────────┬───────────────────────────────┐
    │                     Types                      │           Functions           │
    ├────────────────────────────────────────────────┼───────────────────────────────┤
    │ Specific Type (String, Int, List[Int], etc.)   │ Value (Order 0 Function)      │
    │ Type Constructor (List[Option[A]],List, Option,| Function (Order 1+ Functions) │
    │                 Functor, etc.)                 │                               │
    │ Type Construction                              │ Evaluation                    │
    └────────────────────────────────────────────────┴───────────────────────────────┘
    Type Construction here is a process of Type Constructors application to define a type which can be used as a
    type for variable.


    Also you must have noticed that there are differences in Scala syntax we usually use for functions and for
    type constructors.
    Check following examples:

    ┌─────────────────────────────┬─────────────────────────────────────────────────────────────────────┐
    │            Type             │                              Function                               │
    ├─────────────────────────────┼─────────────────────────────────────────────────────────────────────┤
    │ Option[Content]             │ Content => OptionOfContent                                          │
    │ Map[Key, Value]             │ Key => Value => MapWithKeyAndValue                                  │
    │ Functor[Container[Content]] │ (Content => ContainerWithContent) => FunctorForContainerWithContent │
    └─────────────────────────────┴─────────────────────────────────────────────────────────────────────┘

    For type constructors we usually use underscore to represent type which is not important itself (so we
    don't need to have a name for it), but for which we need to know its kind.

    In the example above it's not important what we have in square brackets, so we can use underscores instead
    of `Content`, `Key`, `Value` and `Container`.
     */

  /* Exercise 6. Fill out orders of the following types:

  ┌───────────────────────┬───────┐
  │         Type          │ Order │ Answer
  ├───────────────────────┼───────┤
  │   Option[_]           │ ???   │ 1
  │                       │       │
  │   Map[_, _]           │ ???   │ 1
  │                       │       │
  │   trait Maybe[T]      │       │ 1
  │   Maybe[_]            │ ???   │ 1
  │                       │       │
  │   trait Functor[F[_]] │       │ 2
  │   Functor[_[_]]       │ ???   │ 2
  └───────────────────────┴───────┘
   */

  /* Exercise 7. Fill out missed kinds for the following types:

  ┌─────────────────────┬──────────────────┐
  │        Type         │       Kind       │ Answer
  ├─────────────────────┼──────────────────┤
  │ String              │ ???              │ *
  │ Option[_]           │ ???              │ * -> *
  │ List[_]             │ ???              │ * -> *
  │ Functor[_[_]]       │ ???              │ (* -> *) -> *
  │ Set[_]              │ ???              │ * -> *
  │ Map[_, _]           │ ???              │ * -> * -> *
  │ Kleisli[_[_], _, _] │ ???              │ (* -> *) -> *) -> * -> * -> *
  └─────────────────────┴──────────────────┘
   */

  /* 3. The intuition behind HKT
  We already know that such types like `List[A]` allow us to abstract over their content. Like it's defined
  for any type `A`, so we can use it with `Int`, `String` or any other type we want.
  The same way functions allow us to abstract over values – their arguments. If we have a function which
  calculates `factorial` it works for any integer we pass into it. We don't have separate implementations
  for `3` or `7` or `42`.

  So in case of Higher Kinded Types or Higher Order Functions same logic continues to work. We abstract over
  types which already abstracts over other type. Or in case of functions we abstract over other functions.


  Now let's move to the practical part and see how all these can be used.

  Let's define type `Maybe` which will be our self-made version of `Option`.
   */

  sealed trait Maybe[+_] // it's equal to `Maybe[+A]`
  object Maybe {
    case object Empty extends Maybe[Nothing]
    case class Something[A](value: A) extends Maybe[A]

    val empty: Maybe[Nothing]            = Empty
    def something[A](value: A): Maybe[A] = Something(value)
  }

  // `Maybe` is defined for any type `A` as we don't use any specifics of A. We abstract over this type.

  // Exercise 8. Implement `Disjunction` – your own version of `Either`
  sealed trait Disjunction[+A, +B]
  object Disjunction {
    case class Left[A, B](value: A) extends Disjunction[A, B]
    case class Right[A, B](value: B) extends Disjunction[A, B]
  }

}
