import org.scalatest.{FunSuite, Matchers}

class CoffeeShopSpec extends FunSuite with Matchers {

  test("buying a cup of coffee should return an order having a cup of coffee with charge of $10") {
    CoffeeShop.buy(MockedCreditCard) should be(Order(Coffee(), Charge(10)))
  }

}
