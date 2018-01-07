import CustomMatchers._
import org.scalatest.{FunSuite, Matchers}


class CoffeeShopSpec extends FunSuite with Matchers with ResetMocks {

  test("should charge total of $11 ($10 + $1 fee) when buying a cup of coffee") {
    CoffeeShop.buy(MockedCreditCard) should be(Coffee())
    MockedCreditCard.chargedAmount should be(11)
  }

  test("should be able to buy 2 cups of coffee") {
    CoffeeShop.buyMany(MockedCreditCard, 2) should be(Seq(Coffee(), Coffee()))
  }

  test("should be able to buy 2 cups of coffee with a total charge of $21 ($10*2 + $1 fee)") {
    CoffeeShop.buyMany(MockedCreditCard, 2) should be(Seq(Coffee(), Coffee()))
    MockedCreditCard should (be(calledOnce) and be(chargedWith(21)))
  }

}
