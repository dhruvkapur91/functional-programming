import org.scalatest.matchers.{BeMatcher, MatchResult}

trait CustomMatchers {

  class CalledOnceMatcher extends BeMatcher[MockCard] {
    override def apply(left: MockCard): MatchResult = MatchResult(
      left.calledNumberOfTimes == 1,
      s"Expected to be called once, but was called ${MockedCreditCard.calledNumberOfTimes} times",
      "")
  }

  class ChargedWithMatcher(charge: Int) extends BeMatcher[MockCard] {
    override def apply(left: MockCard): MatchResult = MatchResult(
      left.chargedAmount == charge,
      s"Expected to be charged $$$charge but was charged $$${left.chargedAmount}",
      "")
  }

  val calledOnce = new CalledOnceMatcher

  def chargedWith(charge: Int) = new ChargedWithMatcher(charge)
}

object CustomMatchers extends CustomMatchers
