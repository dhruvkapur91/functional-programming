import SideEffect.CreditCard
import java.time._

object SideEffect {

  class CreditCard(number: String) {
    def charge(price: Int) = {
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
  def buyMany(creditCard: CreditCard, numberOfCups: Int): Seq[Coffee] = {
    val coffees = (1 to numberOfCups).map(_ => Coffee())
    val `coffee.price` = coffees.map(_.price).sum
    creditCard.charge(`coffee.price`)
    coffees
  }

  def buyMany(purchases: Purchase*): Seq[Coffee] = {
    def _buyManyForOneCreditCard(purchases: Seq[Purchase]) : Seq[Coffee] = {
      val numberOfCoffeesRequired = purchases.map(_.numberOfCups).sum
      if (numberOfCoffeesRequired == 0) Seq.empty else buyMany(purchases.head.creditCard, numberOfCoffeesRequired)
    }

    purchases.groupBy(_.creditCard).values.flatMap(_buyManyForOneCreditCard).toSeq
  }

  def buy(creditCard: CreditCard): Coffee = {
    val coffee = Coffee()
    val `coffee.price` = coffee.price
    creditCard.charge(`coffee.price`)
    coffee
  }
}

object BetrayedProductionUser extends App {
  val someonesCreditCard = new CreditCard("111")
  val twoCoffees = CoffeeShop.buyMany(someonesCreditCard, 2)
  println(twoCoffees)
}