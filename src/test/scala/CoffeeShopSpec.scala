import java.time.LocalDateTime
import java.time.LocalDateTime.now

import org.scalatest.{FunSuite, Matchers}

class CoffeeShopSpec extends FunSuite with Matchers {

  val onePm = LocalDateTime.of(2018, 1, 18, 13, 0)
  val fourPm = LocalDateTime.of(2018, 1, 18, 16, 0)


  test("buying a cup of coffee should return an order having a cup of coffee with price of $10") {
    CoffeeShop.buy() should be(Order(Seq(Coffee()), 10))
  }

  test(
    """buying 2 cup of coffees should return a purchase response
      |containing an order with 2 cups of coffee with price of $20""".stripMargin) {
    val expectedPurchaseResponse = Seq(PurchaseResponse(MockedCreditCard, Order(Seq(Coffee(), Coffee()), 20)))
    CoffeeShop.buyMany(PurchaseRequest(MockedCreditCard, 2, now())) should be(expectedPurchaseResponse)
  }

  test("buying no coffee should return a purchase response containing having 0 cups of coffee with price of $0") {
    val expectedPurchaseResponse = Seq(PurchaseResponse(MockedCreditCard, Order(Seq.empty, 0)))
    CoffeeShop.buyMany(PurchaseRequest(MockedCreditCard, 0, now())) should be(expectedPurchaseResponse)
  }

  test(
    """buying one coffee at 1 pm and another at 4 pm should return a purchase response
      |containing an order having 2 cups with price of $20""".stripMargin) {
    val purchaseRequests = Seq(PurchaseRequest(MockedCreditCard, 1, onePm), PurchaseRequest(MockedCreditCard, 1, fourPm))
    val expectedPurchaseResponse = Seq(PurchaseResponse(MockedCreditCard, Order(Seq(Coffee(), Coffee()), 20)))
    CoffeeShop.buyMany(purchaseRequests: _*) should be(expectedPurchaseResponse)
  }

  test("buying coffee without any purchase request should return 0 cups of coffee with price of 0$") {
    CoffeeShop.buyMany() should be(Seq())
  }

  test(
    """buying 1 coffee using a credit card and 1 coffee using a different credit card
      |should return list of purchaseResponse containing the respective credit card
      |along with one coffee each with price of $10""".stripMargin) {
    val request1 = PurchaseRequest(MockedCreditCard, 1, onePm)
    val request2 = PurchaseRequest(AnotherMockedCreditCard, 1, fourPm)
    val expectedResponse1 = PurchaseResponse(MockedCreditCard, Order(Seq(Coffee()), 10))
    val expectedResponse2 = PurchaseResponse(AnotherMockedCreditCard, Order(Seq(Coffee()), 10))
    val expectedPurchaseResponse = Seq(expectedResponse1, expectedResponse2)
    CoffeeShop.buyMany(request1, request2).toSet should be(expectedPurchaseResponse.toSet)
  }

  test(
    """buying 1 coffee using a credit card and 0 coffee using a different credit card
      |should return list of purchaseResponse containing one coffee for the first credit card
      |and no coffees for the second credit card""".stripMargin) {
    val request1 = PurchaseRequest(MockedCreditCard, 1, onePm)
    val request2 = PurchaseRequest(AnotherMockedCreditCard, 0, fourPm)
    val expectedResponse1 = PurchaseResponse(MockedCreditCard, Order(Seq(Coffee()), 10))
    val expectedResponse2 = PurchaseResponse(AnotherMockedCreditCard, Order(Seq(), 0))
    val expectedPurchaseResponse = Seq(expectedResponse1, expectedResponse2)
    CoffeeShop.buyMany(request1, request2).toSet should be(expectedPurchaseResponse.toSet)
  }
}
