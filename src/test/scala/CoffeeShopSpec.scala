import CustomMatchers._
import org.scalatest.{FunSuite, Matchers}

class CoffeeShopSpec extends FunSuite with Matchers {

  test("should call credit card with total of $10 when buying a cup of coffee") {
    CoffeeShop.buy(MockedCreditCard) should be(Coffee())
    MockedCreditCard should be(calledWith(10))
  }
}
