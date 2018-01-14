import org.scalatest.matchers.{BeMatcher, MatchResult}

trait CustomMatchers {

  class CalledWithMatcher(charge: Int) extends BeMatcher[MockedCard] {
    def apply(left: MockedCard): MatchResult = MatchResult(
      left.calledWith == charge,
      s"Expected to be called with ${charge}, but called with ${left.calledWith}",
      ""
    )
  }
  def calledWith(charge: Int) = new CalledWithMatcher(charge)
}

object CustomMatchers extends CustomMatchers