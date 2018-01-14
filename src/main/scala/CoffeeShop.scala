import java.time.LocalDateTime

import Order.noOrder
import SideEffect.CreditCard

object SideEffect {

  class CreditCard(number: String) {
    def charge(price: Int, currency: String): Unit = ???
  }

}

case class Order(items: Seq[Coffee], price: Int) {
  def +(other : Order) = Order(items ++ other.items, price + other.price)
}

object Order {
  val noOrder = Order(Seq.empty, 0)
}

case class Coffee() {
  val price = 10
}

case class PurchaseRequest(creditCard: CreditCard, numberOfCups : Int, time : LocalDateTime)
case class PurchaseResponse(creditCard: CreditCard, order: Order)

object CoffeeShop {
  def buyMany(purchaseRequests: PurchaseRequest*): Seq[PurchaseResponse] = {

    def _processRequestsFor(creditCard: CreditCard, purchaseRequests: Seq[PurchaseRequest]) = {
      val totalCoffeesRequired = purchaseRequests.map(_.numberOfCups).sum
      val combinedOrders = (1 to totalCoffeesRequired).map(_ => buy()).foldLeft(noOrder)(_ + _)
      PurchaseResponse(creditCard, combinedOrders)
    }

    purchaseRequests.groupBy(_.creditCard).map{
      case (creditCard, purchases) => _processRequestsFor(creditCard, purchases)
    }.toSeq
  }

  def buy(): Order = {
    val coffee = Coffee()
    Order(Seq(coffee), coffee.price)
  }
}
