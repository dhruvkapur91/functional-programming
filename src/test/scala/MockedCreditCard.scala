import SideEffect.CreditCard

case object MockedCreditCard extends CreditCard("Mocked") {
  var chargedAmount : Int = -1
  override def charge(price: Int): String = {
    chargedAmount = price + 1
    ""
  }
}

case object AnotherMockedCreditCard extends CreditCard("Mocked") {
  var chargedAmount : Int = -1
  override def charge(price: Int): String = {
    chargedAmount = price + 1
    ""
  }
}
