import SideEffect.CreditCard

class MockedCreditCard extends CreditCard("Mocked") {
  var calledWith : Int = 0
  override def charge(price: Int): Unit = {
    calledWith = calledWith + price
  }

}

object MockedCreditCard extends MockedCreditCard