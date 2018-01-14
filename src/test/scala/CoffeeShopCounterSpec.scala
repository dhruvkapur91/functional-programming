import java.time.LocalDateTime
import java.time.LocalDateTime.now

import org.scalatest.{FunSuite, Matchers}

class CoffeeShopCounterSpec extends FunSuite with Matchers with ResetMocks {

  val onePm = LocalDateTime.of(2018, 1, 18, 13, 0)
  val fourPm = LocalDateTime.of(2018, 1, 18, 16, 0)

  test("should call credit card with 20$ when buying two cups of coffee") {
    CoffeeShopCounter.buy(PurchaseRequest(MockedCreditCard, numberOfCups = 2, now()))
    MockedCreditCard.calledWith should be(20)
    MockedCreditCard.currencyCalledWith should be("USD")
  }

  test("should not call credit card when not buying any coffee") {
    CoffeeShopCounter.buy(PurchaseRequest(MockedCreditCard, numberOfCups = 0, now()))
    MockedCreditCard.wasCalled should be(false)
  }

  test(
    """
      |when buying one cup of coffee using one card and buying two cups of coffee using another card then
      |it should call first credit card with $10 and call second credit card with $20"
    """.stripMargin) {
    CoffeeShopCounter.buy(PurchaseRequest(MockedCreditCard, 1, onePm), PurchaseRequest(AnotherMockedCreditCard, 2, fourPm))
    MockedCreditCard.calledWith should be(10)
    MockedCreditCard.currencyCalledWith should be("USD")
    AnotherMockedCreditCard.calledWith should be(20)
    AnotherMockedCreditCard.currencyCalledWith should be("USD")
  }
}
