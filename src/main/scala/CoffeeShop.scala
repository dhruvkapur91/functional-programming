import SideEffect.CreditCard

object SideEffect {
  class CreditCard(number : String) {
    def charge(price : Int): Unit = ???
  }
}

case class Coffee() {
  val price = 10
}


object CoffeeShop {
  def buyMany(creditCard: CreditCard, numberOfCups: Int): Seq[Coffee] = {
    (1 to numberOfCups).map(_ => buy(creditCard))
  }


  def buy(creditCard: CreditCard): Coffee = {
    val coffee = Coffee()
    creditCard.charge(coffee.price)
    coffee
  }
}
