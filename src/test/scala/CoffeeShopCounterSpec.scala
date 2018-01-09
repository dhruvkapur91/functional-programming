import java.time.LocalDateTime

import org.scalatest.{FunSuite, Matchers}

class CoffeeShopCounterSpec extends FunSuite with Matchers {
  val onePm = LocalDateTime.of(2018,1,18,13,0)
  val fourPm = LocalDateTime.of(2018,1,18,16,0)

  test(
    """should charge $21(20 + 1 fee)
      |when requesting one coffee at 1 PM and
      |one coffee at 4 pm using same credit card
    """.stripMargin) {
    val firstRequest = Request(MockedCreditCard, 1, onePm)
    val secondRequest = Request(MockedCreditCard, 1, fourPm)
    CoffeeShopCounter.buy(MockedCreditCard, firstRequest, secondRequest)
    MockedCreditCard.chargedAmount should be(21)
  }
}
