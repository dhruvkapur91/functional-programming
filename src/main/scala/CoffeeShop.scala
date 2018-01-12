import SideEffect.CreditCard

object SideEffect {
  class CreditCard(number : String) {
    def charge(price : Int): Unit = {
      println(
        s"""
           |1. Contacted Credit Card company via web service
           |2. Authorizing transaction
           |3. Charging credit card $price
           |4. Charging credit card fee $$1
           |5. Recording transaction in DB
        """.stripMargin)
    }
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

object BetrayedProductionUser extends App {
  val someonesCreditCard = new CreditCard("111")
  val twoCoffees = CoffeeShop.buyMany(someonesCreditCard, 2)
  println(twoCoffees)
}