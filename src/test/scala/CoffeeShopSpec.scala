import org.scalatest.{FunSuite, Matchers}

class CoffeeShopSpec extends FunSuite with Matchers {

  test("buying a cup of coffee should return an order having a cup of coffee with charge of $10") {
    CoffeeShop.buy() should be(Order(Seq(Coffee()), 10))
  }

  test("buying two cups of coffee should return one order having two cup of coffee with charge of $20") {
    CoffeeShop.buyMany(2) should be(Order(Seq(Coffee(),Coffee()), 20))
  }

}
