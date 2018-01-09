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
  def buyMany(requests: Seq[Request]): Seq[Order] = {
    def buyManyForOneCreditCard(requests: Seq[Request]): Order =
      requests.map(request => buyMany(request)).reduce(_ + _)

    requests.groupBy(_.creditCard).values.map(buyManyForOneCreditCard).toSeq
  }

  def buyMany(request: Request): Order = {
    val orders = (1 to request.numberOfCups).map(_ => buy(request.creditCard))
    orders.reduce(_ + _)
  }

  def buy(creditCard: CreditCard): Order = {
    val coffee = Coffee()
    Order(Seq(coffee), Charge(coffee.price, creditCard))
  }
}

object CoffeeShopCounter {
  def buy(creditCard: CreditCard, requests: Request*) = {
    def chargeFromCreditCard(order: Order) =
      order.charge.creditCard.charge(order.charge.price)

    val orders = CoffeeShop.buyMany(requests)

    orders.foreach(chargeFromCreditCard)
    println(orders)
  }
}