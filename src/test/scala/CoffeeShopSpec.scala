import java.time.LocalDateTime
import java.time.LocalDateTime.now

import org.scalatest.{FunSuite, Matchers}

class CoffeeShopSpec extends FunSuite with Matchers {

  val onePm = LocalDateTime.of(2018, 1, 18, 13, 0)
  val fourPm = LocalDateTime.of(2018, 1, 18, 16, 0)


  test("buying a cup of coffee should return an order having a cup of coffee with price of $10") {
    CoffeeShop.buy() should be(Order(Seq(Coffee()), 10))
  }

  test("buying 2 cup of coffees should return an order having 2 cups of coffee with price of $20") {
    CoffeeShop.buyMany(PurchaseRequest(2, now())) should be(Order(Seq(Coffee(), Coffee()), 20))
  }

  test("buying no coffee should return an order having 0 cups of coffee with price of $0") {
    CoffeeShop.buyMany(PurchaseRequest(0, now())) should be(Order(Seq.empty, 0))
  }

  test("buying one coffee at 1 pm and another at 4 pm should return an order having 2 cups with price of $20") {
    val purchaseRequests = Seq(PurchaseRequest(1, onePm), PurchaseRequest(1, fourPm))
    CoffeeShop.buyMany(purchaseRequests: _*) should be(Order(Seq(Coffee(), Coffee()), 20))
  }

  test("buying coffee without any purchase request should return 0 cups of coffee with price of 0$") {
    CoffeeShop.buyMany() should be(Order(Seq.empty, 0))
  }

}
