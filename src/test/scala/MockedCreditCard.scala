import SideEffect.CreditCard

class MockedCreditCard extends CreditCard("Mocked") {
  var calledWith : Int = 0
  var wasCalled : Boolean = false
  override def charge(price: Int): Unit = {
    wasCalled = true
    calledWith = calledWith + price
  }

}

object MockedCreditCard extends MockedCreditCard {
  def reset(): Unit = {
    MockedCreditCard.calledWith = 0
    MockedCreditCard.wasCalled = false
  }
}

class AnotherMockedCreditCard extends CreditCard("Mocked") {
  var calledWith : Int = 0
  var wasCalled : Boolean = false
  override def charge(price: Int): Unit = {
    wasCalled = true
    calledWith = calledWith + price
  }

}

object AnotherMockedCreditCard extends AnotherMockedCreditCard {
  def reset(): Unit = {
    MockedCreditCard.calledWith = 0
    MockedCreditCard.wasCalled = false
  }
}