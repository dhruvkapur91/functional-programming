import SideEffect.CreditCard

object SideEffect {

  class CreditCard(number: String) {
    def charge(price: Int): String = ???
  }

}

case class Coffee() {
  val price = 10
}

case class Order(coffee: Coffee, price: Int)

object CoffeeShop {

  def buy(): Order = {
    val coffee = Coffee()
    Order(coffee, coffee.price)
  }

}

object CoffeeShopCounter {
  def buy(creditCard: CreditCard) = {
    val order: Order = CoffeeShop.buy()
    creditCard.charge(order.price)
    println(order)
  }
}