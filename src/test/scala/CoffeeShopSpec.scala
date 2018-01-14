import org.scalatest.{FunSuite, Matchers}

class CoffeeShopSpec extends FunSuite with Matchers {


  test("buying a cup of coffee should return an order having a cup of coffee with price of $10") {
    CoffeeShop.buy() should be(Order(Seq(Coffee()), 10))
  }

//  test("should call credit card with total of $20 when buying 2 cups of coffee") {
//    CoffeeShop.buyMany(MockedCreditCard, 2) should be(Seq(Coffee(), Coffee()))
//    MockedCreditCard should (be(calledOnce) and be(calledWith(20)))
//  }

  test("buying 2 cup of coffees should return an order having 2 cups of coffee with price of $20") {
    CoffeeShop.buyMany(2) should be(Order(Seq(Coffee(),Coffee()), 20))
  }

}
