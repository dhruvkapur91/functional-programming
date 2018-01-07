import org.scalatest.{BeforeAndAfterEach, FunSuite}


trait ResetMocks extends BeforeAndAfterEach {
  this : FunSuite =>

  override protected def beforeEach(): Unit = {
    MockedCreditCard.reset()
    AnotherMockedCreditCard.reset()
  }

}
