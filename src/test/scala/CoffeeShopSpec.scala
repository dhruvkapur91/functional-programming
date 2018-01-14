import org.scalatest.{FunSuite, Matchers}

class CoffeeShopSpec extends FunSuite with Matchers {

  test("buying a cup of coffee should return an order having a cup of coffee with price of $10") {
    CoffeeShop.buy() should be(Order(Coffee(), 10))
  }

}
