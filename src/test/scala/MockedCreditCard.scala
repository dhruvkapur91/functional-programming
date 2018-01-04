import SideEffect.CreditCard

case object MockedCreditCard extends CreditCard("Mocked") {
  var chargedAmount : Int = -1
  var calledNumberOfTimes : Int = 0
  override def charge(price: Int) = {
    chargedAmount = price + 1
    calledNumberOfTimes += 1
  }
  def reset() = {
    chargedAmount = -1
    calledNumberOfTimes = 0
  }
}
