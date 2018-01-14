import SideEffect.CreditCard

object SideEffect {

  class CreditCard(number: String) {
    def charge(price: Int): Unit = ???
  }

}

case class Order(items: Coffee, price: Int)

case class Coffee() {
  val price = 10
}

object CoffeeShop {

  def buy(): Order = {
    val coffee = Coffee()
    //    creditCard.charge(coffee.price)
    Order(coffee, coffee.price)
  }
}
