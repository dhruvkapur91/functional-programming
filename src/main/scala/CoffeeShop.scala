import SideEffect.CreditCard

object SideEffect {

  class CreditCard(number: String) {
    def charge(price: Int): Unit = ???
  }

}

case class Charge(price: Int, creditCard: CreditCard)

case class Order(items: Coffee, charge: Charge)

case class Coffee() {
  val price = 10
}

object CoffeeShop {

  def buy(creditCard: CreditCard): Order = {
    val coffee = Coffee()
    //    creditCard.charge(coffee.price)
    Order(coffee, Charge(coffee.price, creditCard))
  }
}
