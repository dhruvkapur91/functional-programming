import org.scalatest.{FunSuite, Matchers}

class CoffeeShopCounterSpec extends FunSuite with Matchers {
  test("should charge $21(20 + 1 fee) when buying two cups of coffee") {
    CoffeeShopCounter.buy(MockedCreditCard, numberOfCups = 2)
    MockedCreditCard.chargedAmount should be(21)
  }
}
