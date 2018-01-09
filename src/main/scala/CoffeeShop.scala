import java.time.LocalDateTime

import SideEffect.CreditCard

object SideEffect {

  class CreditCard(number: String) {
    def charge(price: Int): String = ???
  }

}

case class Coffee() {
  val price = 10
}

case class Charge(price: Int, creditCard: CreditCard) {
  def +(other: Charge): Charge =
    if (creditCard == other.creditCard) Charge(price + other.price, creditCard)
    else throw new Exception("Cards are different")
}

case class Order(items: Seq[Coffee], charge: Charge) {
  def +(other: Order) = Order(items ++ other.items, charge + other.charge)
}

case class Request(creditCard: CreditCard, numberOfCups: Int, time: LocalDateTime)

object CoffeeShop {
  def buyMany(requests: Seq[Request]): Order = {
    require(creditCardShouldBeSameForAll(requests))

    val orders = requests.map(request => buyMany(request.creditCard, request.numberOfCups))
    orders.reduce(_ + _)
  }

  def buyMany(creditCard: CreditCard, numberOfCups: Int): Order = {
    val orders = (1 to numberOfCups).map(_ => buy(creditCard))
    orders.reduce(_ + _)
  }

  def buy(creditCard: CreditCard): Order = {
    val coffee = Coffee()
    Order(Seq(coffee), Charge(coffee.price, creditCard))
  }

  private def creditCardShouldBeSameForAll(requests: Seq[Request]): Boolean = {
    requests.map(_.creditCard).toSet.size == 1
  }

}

object CoffeeShopCounter {
  def buy(creditCard: CreditCard, requests: Request*) = {
    val order = CoffeeShop.buyMany(requests)
    creditCard.charge(order.charge.price)
    println(order)
  }
}