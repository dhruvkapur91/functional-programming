import SideEffect.CreditCard

case object MockedCreditCard extends CreditCard("Mocked") {
  var chargedAmount : Int = -1
  var calledWith : Int = -1
  override def charge(price: Int): String = {
    calledWith = price
    chargedAmount = price + 1
    ""
  }
}
