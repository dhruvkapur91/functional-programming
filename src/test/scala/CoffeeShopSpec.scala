import java.time.LocalDateTime

import CustomMatchers._
import org.scalatest.{FunSuite, Matchers}

class CoffeeShopSpec extends FunSuite with Matchers with ResetMocks {

  test("should call credit card with total of $10 when buying a cup of coffee") {
    CoffeeShop.buy(MockedCreditCard) should be(Coffee())
    MockedCreditCard should be(calledWith(10))
  }

  test("should call credit card with total of $20 when buying 2 cups of coffee") {
    val now = LocalDateTime.now()
    CoffeeShop.buyMany(Purchase(MockedCreditCard, 2, now)) should be(Seq(Coffee(), Coffee()))
    MockedCreditCard should (be(calledOnce) and be(calledWith(20)))
  }

  test("should not call credit card and not return coffee when number of cups ordered is 0") {
    val now = LocalDateTime.now()
    CoffeeShop.buyMany(Purchase(MockedCreditCard, 0, now)) should be(Seq())
    MockedCreditCard.calledNumberOfTimes should be(0)
  }
}