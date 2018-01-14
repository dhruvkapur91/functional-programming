import org.scalatest.{FunSuite, Matchers}

class CoffeeShopCounterSpec extends FunSuite with Matchers {
  test("should call credit card with total of $20 when buying two cups of coffee") {
    CoffeeShopCounter.buy(MockedCreditCard, numberOfCups = 2)
    MockedCreditCard.calledWith should be(20)
  }
}
