import org.scalatest.matchers.{BeMatcher, MatchResult}

trait CustomMatchers {

  class CalledOnceMatcher extends BeMatcher[MockCard] {
    def apply(left: MockCard): MatchResult = MatchResult(
      left.calledNumberOfTimes == 1,
      s"Expected to be called once, but called ${left.calledNumberOfTimes} number of times",
      ""
    )
  }
  class CalledWithMatcher(charge: Int, currency : String) extends BeMatcher[MockCard] {
    def apply(left: MockCard): MatchResult = MatchResult(
      left.calledWith == charge && left.currency == currency,
      s"Expected to be called with $charge $currency, but called with ${left.calledWith} ${left.currency} ",
      ""
    )
  }

  def calledOnce = new CalledOnceMatcher
  def calledWith(charge: Int, currency : String) = new CalledWithMatcher(charge, currency)
}

object CustomMatchers extends CustomMatchers