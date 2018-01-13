import java.time.LocalDateTime

import CustomMatchers._
import org.scalatest.{FunSuite, Matchers}

class CoffeeShopSpec extends FunSuite with Matchers with ResetMocks {

  val onePm = LocalDateTime.of(2018, 1, 18, 13, 0)
  val fourPm = LocalDateTime.of(2018, 1, 18, 16, 0)

  test("should call credit card with total of $10 when buying a cup of coffee") {
    CoffeeShop.buy(MockedCreditCard) should be(Coffee())
    MockedCreditCard should be(calledWith(10, "USD"))
  }

  test("should call credit card with total of $20 when buying 2 cups of coffee") {
    val now = LocalDateTime.now()
    CoffeeShop.buyMany(Purchase(MockedCreditCard, 2, now)) should be(Seq(Coffee(), Coffee()))
    MockedCreditCard should (be(calledOnce) and be(calledWith(20, "USD")))
  }

  test("should not call credit card and not return coffee when number of cups ordered is 0") {
    val now = LocalDateTime.now()
    CoffeeShop.buyMany(Purchase(MockedCreditCard, 0, now)) should be(Seq())
    MockedCreditCard.calledNumberOfTimes should be(0)
  }

  test(
    """
      | purchasing one coffee at 1 pm and one coffee at 4 pm using same credit card
      | should call credit card only once with total of $20
    """.stripMargin) {
    val firstPurchase = Purchase(MockedCreditCard, 1, onePm)
    val secondPurchase = Purchase(MockedCreditCard, 1, fourPm)
    CoffeeShop.buyMany(firstPurchase, secondPurchase) should be(Seq(Coffee(), Coffee()))
    MockedCreditCard should (be(calledOnce) and be(calledWith(20, "USD")))
  }

  test(
    """
      | purchasing one coffee using one credit card and purchasing one coffee using separate credit card
      | should call each credit card only once with total of $20
    """.stripMargin) {
    val firstPurchase = Purchase(MockedCreditCard, 1, onePm)
    val secondPurchase = Purchase(AnotherMockedCreditCard, 1, fourPm)
    CoffeeShop.buyMany(firstPurchase, secondPurchase) should be(Seq(Coffee(), Coffee()))
    MockedCreditCard should (be(calledOnce) and be(calledWith(10, "USD")))
    AnotherMockedCreditCard should (be(calledOnce) and be(calledWith(10, "USD")))
  }
}