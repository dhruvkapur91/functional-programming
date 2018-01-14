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


object CoffeeShop {
  def buyMany(numberOfCups: Int): Order = {
    (1 to numberOfCups)
      .map(_ => buy())
      .foldLeft(noOrder)((one, two) => one + two)
  }


  def buy(): Order = {
    val coffee = Coffee()
    Order(Seq(coffee), coffee.price)
  }
}
