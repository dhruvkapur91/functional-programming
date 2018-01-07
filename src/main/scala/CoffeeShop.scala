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

case class Charge(price: Int)

object Charge {
  def totalCharge(charges : Seq[Charge]) = Charge(charges.map(_.price).sum)
}

case class Item(coffee: Coffee, charge: Charge)

case class Order(items: Seq[Item], charge: Charge)

object CoffeeShop {
  import Charge._

  def buyMany(creditCard: CreditCard, numberOfCups: Int): Order = {
    val items = (1 to numberOfCups).map(_ => buy(creditCard))
    Order(items, totalCharge(items.map(_.charge)))
  }


  def buy(creditCard: CreditCard): Item = {
    val coffee = Coffee()
    Item(coffee, Charge(coffee.price))
  }

}

object CoffeeShopCounter {
  def buy(creditCard: CreditCard) = {
    val order: Item = CoffeeShop.buy(creditCard)
    creditCard.charge(order.charge.price)
    println(order)
  }
}