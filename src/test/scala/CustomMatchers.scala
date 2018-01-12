import org.scalatest.matchers.{BeMatcher, MatchResult}

trait CustomMatchers {

  class CalledOnceMatcher extends BeMatcher[MockCard] {
    def apply(left: MockCard): MatchResult = MatchResult(
      left.calledNumberOfTimes == 1,
      s"Expected to be called once, but called ${left.calledNumberOfTimes} number of times",
      ""
    )
  }
  class CalledWithMatcher(charge: Int) extends BeMatcher[MockCard] {
    def apply(left: MockCard): MatchResult = MatchResult(
      left.calledWith == charge,
      s"Expected to be called with ${charge}, but called with ${left.calledWith}",
      ""
    )
  }

  def calledOnce = new CalledOnceMatcher
  def calledWith(charge: Int) = new CalledWithMatcher(charge)
}

object CustomMatchers extends CustomMatchers