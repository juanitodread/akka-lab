package org.juanitodread.helloworldtyped.actor

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

object GreeterBot {
  def apply(max: Int): Behavior[Greeter.Greeted] = {
    bot(0, max)
  }

  def bot(greetingCounter: Int, max: Int): Behavior[Greeter.Greeted] = {
    Behaviors.receive { (context, message) =>
      val messageCount = greetingCounter + 1
      context.log.info(s"Greeting $messageCount for ${message.whom}")

      if (messageCount == max) {
        Behaviors.stopped
      } else {
        message.from ! Greeter.Greet(message.whom, context.self)
        bot(messageCount, max)
      }
    }
  }
}
