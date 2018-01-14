import SideEffect.CreditCard

object CoffeeShopCounter {
  def buy(creditCard: CreditCard) : Unit = {
    val order = CoffeeShop.buy()
    creditCard.charge(order.price)
    println(order)
  }
}
