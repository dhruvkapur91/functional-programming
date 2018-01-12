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
}