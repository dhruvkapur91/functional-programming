import org.scalatest.{FunSuite, Matchers}

class CoffeeShopSpec extends FunSuite with Matchers {

  test("buying a cup of coffee should return an order having a cup of coffee with charge of $10") {
    val coffee = Item(Coffee(), Charge(10))
    CoffeeShop.buy(MockedCreditCard) should be(Order(Seq(coffee), Charge(10)))
  }

  test("buying two cups of coffee should return one order having two cup of coffee with charge of $20") {
    val firstCoffee = Item(Coffee(), Charge(10))
    val secondCoffee = Item(Coffee(), Charge(10))
    CoffeeShop.buyMany(MockedCreditCard, 2) should be(Order(Seq(firstCoffee, secondCoffee), Charge(20)))
  }

}
