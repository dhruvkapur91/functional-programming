import java.time.LocalDateTime

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

case class Purchase(creditCard: CreditCard, numberOfCups: Int, time: LocalDateTime)

object CoffeeShop {

  def buyMany(purchases: Purchase*): Seq[Coffee] = {
    require(allPurchasesMadeUsingSameCreditCard(purchases))
    val numberOfCupsRequired = purchases.map(_.numberOfCups).sum
    buyMany(Purchase(purchases.head.creditCard, numberOfCupsRequired, LocalDateTime.now()))
  }

  def buyMany(purchase: Purchase): Seq[Coffee] = {
    if(purchase.numberOfCups == 0) Seq.empty else {
      val coffees = (1 to purchase.numberOfCups).map(_ => Coffee())
      val price = coffees.map(_.price).sum
      purchase.creditCard.charge(price)
      coffees
    }
  }

  def buy(creditCard: CreditCard): Coffee = {
    val coffee = Coffee()
    creditCard.charge(coffee.price)
    coffee
  }

  private def allPurchasesMadeUsingSameCreditCard(purchases: Seq[Purchase]): Boolean = {
    purchases.map(_.creditCard).toSet.size == 1
  }
}

object BetrayedProductionUser extends App {
  val someonesCreditCard = new CreditCard("111")
  val twoCoffees = CoffeeShop.buyMany(Purchase(someonesCreditCard, 2, LocalDateTime.now()))
  println(twoCoffees)
}