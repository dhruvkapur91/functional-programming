import SideEffect.CreditCard

abstract class MockCard(number: String) extends CreditCard(number) {
  var calledWith: Int = 0
  var calledNumberOfTimes: Int = 0
  override def charge(price: Int): Unit = {
    calledWith = calledWith + price
    calledNumberOfTimes += 1
  }
  def reset(): Unit = {
    calledWith = 0
    calledNumberOfTimes = 0
  }
}

case object MockedCreditCard extends MockCard("Mocked")