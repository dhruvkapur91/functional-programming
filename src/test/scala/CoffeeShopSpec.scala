import java.time.LocalDateTime

import CustomMatchers._
import org.scalatest.{FunSuite, Matchers}


class CoffeeShopSpec extends FunSuite with Matchers with ResetMocks {

  val onePm = LocalDateTime.of(2018,1,18,13,0)
  val fourPm = LocalDateTime.of(2018,1,18,16,0)

  test("should charge total of $11 ($10 + $1 fee) when buying a cup of coffee") {
    CoffeeShop.buy(MockedCreditCard) should be(Coffee())
    MockedCreditCard.chargedAmount should be(11)
    // charge should be(10)
  }

  test("should be able to buy 2 cups of coffee") {
    CoffeeShop.buyMany(MockedCreditCard, 2) should be(Seq(Coffee(), Coffee()))
  }

  test("should be able to buy 2 cups of coffee with a total charge of $21 ($10*2 + $1 fee)") {
    CoffeeShop.buyMany(MockedCreditCard, 2) should be(Seq(Coffee(), Coffee()))
    MockedCreditCard should (be(calledOnce) and be(chargedWith(21))) // Business doesn't care about calledOnce - tech detail
    // charge should be(20)
  }

  test(
    """
      |should charge total of $21 ($10*2 + $1 fee)
      |when buying 1 coffee at 1 PM and 1 coffee at 4 PM using same credit card
    """.stripMargin) {

    CoffeeShop.buyMany(
      Purchase(MockedCreditCard, 1, onePm),
      Purchase(MockedCreditCard, 1, fourPm)) should be(Seq(Coffee(), Coffee()))

    MockedCreditCard should (be(calledOnce) and be(chargedWith(21)))
  }

  test(
    """
      |should charge total of $11 ($10 + $1 fee) to each card
      |when buying coffees using different credit cards
    """.stripMargin) {

    CoffeeShop.buyMany(
      Purchase(MockedCreditCard, 1, onePm),
      Purchase(AnotherMockedCreditCard, 1, fourPm)) should be(Seq(Coffee(), Coffee()))

    MockedCreditCard should (be(calledOnce) and be(chargedWith(11)))
    AnotherMockedCreditCard should (be(calledOnce) and be(chargedWith(11)))
  }
}
