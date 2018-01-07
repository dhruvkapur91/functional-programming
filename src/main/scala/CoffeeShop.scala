import SideEffect.CreditCard

object SideEffect {
  class CreditCard(number : String) {
    def charge(price : Int) : String = ???
  }
}

case class Coffee() {
  val price = 10
}

case class Charge(price: Int)

case class Order(coffee: Coffee, charge: Charge)

object CoffeeShop {

  def buy(creditCard: CreditCard): Order = {
    val coffee = Coffee()
    Order(coffee, Charge(coffee.price))
  }

}
