import SideEffect.CreditCard

abstract class MockedCard(number: String) extends CreditCard(number) {
  var calledWith : Int = 0
  var wasCalled : Boolean = false
  var currencyCalledWith: String = ""
  override def charge(price: Int, currency: String): Unit = {
    wasCalled = true
    calledWith = calledWith + price
    currencyCalledWith = currency
  }

  def reset(): Unit = {
    calledWith = 0
    wasCalled = false
    currencyCalledWith = ""
  }

}

object MockedCreditCard extends MockedCard("Mocked")

object AnotherMockedCreditCard extends MockedCard("Another Mocked")