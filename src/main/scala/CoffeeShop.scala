import SideEffect.CreditCard

object SideEffect {

  class CreditCard(number: String) {
    def charge(price: Int): String = ???
  }

}

case class Coffee() {
  val price = 10
}

case class Order(coffees: Seq[Coffee], price: Int) {
  def +(other : Order) = Order(coffees ++ other.coffees, price + other.price)
}

object CoffeeShop {
  def buyMany(numberOfCups: Int) : Order = {
    val orders = (1 to numberOfCups).map(_ => buy())
    orders.reduce((one,two) => one + two)
  }


  def buy(): Order = {
    val coffee = Coffee()
    Order(Seq(coffee), coffee.price)
  }

}

object CoffeeShopCounter {
  def buy(creditCard: CreditCard) = {
    val order: Order = CoffeeShop.buy()
    creditCard.charge(order.price)
    println(order)
  }
}