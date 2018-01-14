import java.time.LocalDateTime
import java.time.LocalDateTime.now

import SideEffect.CreditCard

object CoffeeShopCounter {
  def buy(creditCard: CreditCard, numberOfCups : Int = 0) : Unit = {
    val order = CoffeeShop.buyMany(PurchaseRequest(numberOfCups,now))
    if(order.items.nonEmpty) creditCard.charge(order.price)
    println(order)
  }
}
