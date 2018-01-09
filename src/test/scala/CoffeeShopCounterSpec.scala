import java.time.LocalDateTime

import org.scalatest.{FunSuite, Matchers}

class CoffeeShopCounterSpec extends FunSuite with Matchers {
  val onePm = LocalDateTime.of(2018,1,18,13,0)
  val fourPm = LocalDateTime.of(2018,1,18,16,0)

  test(
    """should charge $11(10 + 1 fee) to each card
      |when requesting two coffees using separate credit cards
    """.stripMargin) {
    val firstRequest = Request(MockedCreditCard, 1, onePm)
    val secondRequest = Request(AnotherMockedCreditCard, 1, onePm)
    CoffeeShopCounter.buy(MockedCreditCard, firstRequest, secondRequest)
    MockedCreditCard.chargedAmount should be(11)
    AnotherMockedCreditCard.chargedAmount should be(11)
  }
}
