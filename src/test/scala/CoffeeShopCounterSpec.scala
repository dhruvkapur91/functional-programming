import org.scalatest.{FunSuite, Matchers}

class CoffeeShopCounterSpec extends FunSuite with Matchers {
  test("should charge $11(10 + 1 fee) when buying a cup of coffee") {
    CoffeeShopCounter.buy(MockedCreditCard)
    MockedCreditCard.chargedAmount should be(11)
  }
}
