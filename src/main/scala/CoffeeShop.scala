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
    val coffees = (1 to numberOfCups).map(_ => `Coffee`()) // Logic to create one coffee duplicated
    val `coffee.price` = coffees.map(_.price).sum
    creditCard.charge(`coffee.price`) // Logic to charge a credit card duplicated
    coffees
  }


  def buy(creditCard: CreditCard): Coffee = {
    val coffee = `Coffee`() // Logic to create one coffee
    val `coffee.price` = coffee.price
    creditCard.charge(`coffee.price`) // Logic to charge a credit card
    coffee
  }
}

object BetrayedProductionUser extends App {
  val someonesCreditCard = new CreditCard("111")
  val twoCoffees = CoffeeShop.buyMany(someonesCreditCard, 2)
  println(twoCoffees)
}