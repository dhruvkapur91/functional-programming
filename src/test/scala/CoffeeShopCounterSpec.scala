import org.scalatest.{FunSuite, Matchers}

class CoffeeShopCounterSpec extends FunSuite with Matchers with ResetMocks {

  test("should call credit card with 20$ when buying two cups of coffee") {
    CoffeeShopCounter.buy(MockedCreditCard, numberOfCups = 2)
    MockedCreditCard.calledWith should be(20)
  }

  test("should not call credit card when not buying any coffee") {
    CoffeeShopCounter.buy(MockedCreditCard, numberOfCups = 0)
    MockedCreditCard.wasCalled should be(false)
  }

}
