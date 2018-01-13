import org.scalatest.{FunSuite, Matchers}
import CustomMatchers._

class CoffeeShopCounterSpec extends FunSuite with Matchers {
  test("should charge $10 when buying a cup of coffee") {
    CoffeeShopCounter.buy(MockedCreditCard)
    MockedCreditCard should be(calledWith(10))
  }
}
