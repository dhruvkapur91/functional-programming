import org.scalatest.{FunSuite, Matchers}

class CoffeeShopSpec extends FunSuite with Matchers {


  test("buying a cup of coffee should return an order having a cup of coffee with price of $10") {
    CoffeeShop.buy() should be(Order(Seq(Coffee()), 10))
  }

  test("buying 2 cup of coffees should return an order having 2 cups of coffee with price of $20") {
    CoffeeShop.buyMany(2) should be(Order(Seq(Coffee(), Coffee()), 20))
  }

  test("buying no coffee should return an order having 0 cups of coffee with price of $0") {
    CoffeeShop.buyMany(0) should be(Order(Seq.empty, 0))
  }

}
