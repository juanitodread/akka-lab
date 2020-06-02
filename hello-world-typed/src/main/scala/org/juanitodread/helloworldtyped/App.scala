package org.juanitodread.helloworldtyped

import akka.actor.typed.ActorSystem
import org.juanitodread.helloworldtyped.actor.GreeterMain
import org.juanitodread.helloworldtyped.actor.GreeterMain.SayHello

object App {
  def main(args: Array[String]): Unit = {
    val greeterMain: ActorSystem[GreeterMain.SayHello] = ActorSystem(GreeterMain(), "AkkaQuickStart")
    greeterMain ! SayHello("Ztar")
    greeterMain ! SayHello("John")
    greeterMain ! SayHello("Frida")
  }
}
