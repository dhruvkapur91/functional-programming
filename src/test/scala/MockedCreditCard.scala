import SideEffect.CreditCard

abstract class MockCard(number: String) extends CreditCard(number) {
  var calledWith: Int = 0
  var calledNumberOfTimes: Int = 0
  var currency = ""
  override def charge(price: Int, currency : String): Unit = {
    calledWith = calledWith + price
    calledNumberOfTimes += 1
    this.currency = currency
  }
  def reset(): Unit = {
    calledWith = 0
    calledNumberOfTimes = 0
  }
}

case object MockedCreditCard extends MockCard("Mocked")
case object AnotherMockedCreditCard extends MockCard("AnotherMocked")