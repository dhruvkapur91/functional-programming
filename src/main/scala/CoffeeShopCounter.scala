import java.time.LocalDateTime.now

import SideEffect.CreditCard

object CoffeeShopCounter {
  def buy(purchaseRequests: PurchaseRequest*): Unit = {
    val purchaseResponses = CoffeeShop.buyMany(purchaseRequests: _*)
    purchaseResponses.foreach(purchaseResponse =>
      if (purchaseResponse.order.items.nonEmpty)
        purchaseResponse.creditCard.charge(purchaseResponse.order.price))
    println(purchaseResponses)
  }
}
