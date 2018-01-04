import SideEffect.CreditCard

case object MockedCreditCard extends CreditCard("Mocked") {
  var chargedAmount : Int = -1
  override def charge(price: Int) = {
    chargedAmount = price + 1
  }
}
