package org.juanitodread.helloworldtyped.actor

import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit
import org.juanitodread.helloworldtyped.actor.Greeter.{ Greet, Greeted }
import org.scalatest.wordspec.AnyWordSpecLike

class AkkaQuickstartSpec extends ScalaTestWithActorTestKit with AnyWordSpecLike {
  "A Greeter" must {
    "reply to greeted" in {
      val replyProbe = createTestProbe[Greeted]()
      val underTest = spawn(Greeter())

      underTest ! Greet("Robin", replyProbe.ref)
      replyProbe.expectMessage(Greeted("Robin", underTest.ref))
    }
  }
}
