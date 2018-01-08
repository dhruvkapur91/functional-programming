import SideEffect.CreditCard

import scala.collection.immutable.IndexedSeq

object SideEffect {

  class CreditCard(number: String) {
    def charge(price: Int): String = ???
  }

}

case class Coffee() {
  val price = 10
}

case class Charge(price: Int) {
  def +(other: Charge): Charge = Charge(price + other.price)
}

case class Order(items: Seq[Coffee], charge: Charge) {
  def +(other: Order) = Order(items ++ other.items, charge + other.charge)
}

object CoffeeShop {

  def buyMany(creditCard: CreditCard, numberOfCups: Int): Order = {
    val orders = (1 to numberOfCups).map(_ => buy(creditCard))
    orders.reduce(_ + _)
  }


  def buy(creditCard: CreditCard): Order = {
    val coffee = Coffee()
    Order(Seq(coffee), Charge(coffee.price))
  }

}

object CoffeeShopCounter {
  def buy(creditCard: CreditCard, numberOfCups: Int = 1) = {
    val order: Order = CoffeeShop.buyMany(creditCard, numberOfCups)
    creditCard.charge(order.charge.price)
    println(order)
  }
}