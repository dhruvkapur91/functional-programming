import java.time.LocalDateTime

import Order.noOrder

object SideEffect {

  class CreditCard(number: String) {
    def charge(price: Int): Unit = ???
  }

}

case class Order(items: Seq[Coffee], price: Int) {
  def +(other : Order) = Order(items ++ other.items, price + other.price)
}

object Order {
  val noOrder = Order(Seq.empty,0)
}

case class Coffee() {
  val price = 10
}

case class PurchaseRequest(numberOfCups : Int, time : LocalDateTime)


object CoffeeShop {
  def buyMany(purchaseRequest: PurchaseRequest*): Order = {
    val totalCoffeesRequired = purchaseRequest.map(_.numberOfCups).sum
    (1 to totalCoffeesRequired).map(_ => buy()).foldLeft(noOrder)(_ + _)
  }


  def buy(): Order = {
    val coffee = Coffee()
    Order(Seq(coffee), coffee.price)
  }
}
