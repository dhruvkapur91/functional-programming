import SideEffect.CreditCard

abstract class MockCard(number: String) extends CreditCard(number) {
  var chargedAmount: Int = -1
  var calledNumberOfTimes: Int = 0

  override def charge(price: Int) = {
    chargedAmount = price + 1
    calledNumberOfTimes += 1
  }

  def reset() = {
    chargedAmount = -1
    calledNumberOfTimes = 0
  }
}

case object MockedCreditCard extends MockCard("Mocked")

case object AnotherMockedCreditCard extends MockCard("AnotherMocked")