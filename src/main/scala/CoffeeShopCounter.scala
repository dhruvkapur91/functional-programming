import SideEffect.CreditCard

object CoffeeShopCounter {
  def buy(creditCard: CreditCard, numberOfCups : Int = 0) : Unit = {
    val order = CoffeeShop.buyMany(numberOfCups)
    if(order.items.nonEmpty) creditCard.charge(order.price)
    println(order)
  }
}
