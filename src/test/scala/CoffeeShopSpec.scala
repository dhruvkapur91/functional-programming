import java.time.LocalDateTime

import org.scalatest.{FunSuite, Matchers}

class CoffeeShopSpec extends FunSuite with Matchers {

  val onePm = LocalDateTime.of(2018,1,18,13,0)
  val fourPm = LocalDateTime.of(2018,1,18,16,0)

  test("buying a cup of coffee should return an order having a cup of coffee with charge of $10") {
    val coffee = Coffee()
    CoffeeShop.buy(MockedCreditCard) should be(Order(Seq(coffee), Charge(10, MockedCreditCard)))
  }

  test("buying two cups of coffee should return one order having two cup of coffee with charge of $20") {
    CoffeeShop.buyMany(MockedCreditCard, 2) should be(Order(Seq(Coffee(), Coffee()), Charge(20, MockedCreditCard)))
  }

  test(
    """requesting two cups separately at 1 PM and 4 pm from same card
      |should return an order of two cups with a charge of $20""".stripMargin) {
    val firstRequest = Request(MockedCreditCard, 1, onePm)
    val secondRequest = Request(MockedCreditCard, 1, onePm)
    val coffees = Seq(Coffee(), Coffee())
    CoffeeShop.buyMany(Seq(firstRequest, secondRequest)) should be(Order(coffees, Charge(20, MockedCreditCard)))
  }
}
