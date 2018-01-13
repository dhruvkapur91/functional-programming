import org.scalatest.FunSuite

object Pure {
  def hello() = "hello"
  def world() = "world"
}

class PureFunctionsAreModular extends FunSuite {
  test("Print helloworld") {
    import Pure._
    print(hello())
    print(world())
  }

  test("Refactor two print calls into one, is that okay?") {
    import Pure._
    print(hello() + world())
  }
}

object Impure {
  def hello() = {
    println("hello")
    "hello"
  }

  def world() = {
    println("world")
    "world"
  }
}

class SideEffectsBreakModularity extends FunSuite {
  test("Print helloworld") {
    import Impure._
    print(hello())
    print(world())

  }

  test("Refactor two print calls into one, is that okay?") {
    import Impure._
    print(hello() + world())
  }
}
