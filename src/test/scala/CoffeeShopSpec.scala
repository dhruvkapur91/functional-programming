import CustomMatchers._
import org.scalatest.{FunSuite, Matchers}

class CoffeeShopSpec extends FunSuite with Matchers {

  test("should call credit card with total of $10 when buying a cup of coffee") {
    CoffeeShop.buy(MockedCreditCard) should be(Coffee())
    MockedCreditCard should be(calledWith(10))
  }

  test("should be able to buy 2 cups of coffee") {
    CoffeeShop.buyMany(MockedCreditCard, 2) should be(Seq(Coffee(), Coffee()))
  }
}
