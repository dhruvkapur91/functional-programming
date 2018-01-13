import org.scalatest.{FunSuite, Matchers}

class CoffeeShopSpec extends FunSuite with Matchers {

//  test("should call credit card with total of $10 when buying a cup of coffee") {
//    CoffeeShop.buy(MockedCreditCard) should be(Coffee())
//    MockedCreditCard should be(calledWith(10))
//  }

  test("buying a cup of coffee should return an order having a cup of coffee with charge of $10") {
    val coffee = Coffee()
    CoffeeShop.buy(MockedCreditCard) should be(Order(coffee, Charge(10, MockedCreditCard)))
  }
}
