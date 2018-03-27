/**
 * hello-world-akka
 *
 * Copyright 2016 juanitodread
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.juanitodread.helloworld

import org.juanitodread.helloworld.model._
import org.juanitodread.helloworld.actor._

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.Inbox
import akka.actor.ActorRef

import scala.concurrent.duration._

object Main {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("hello-akka")

    val greeter = system.actorOf(Props[Greeter], "greeter")

    val inbox = Inbox.create(system)

    greeter.tell(WhoToGreet("akka"), ActorRef.noSender)

    inbox.send(greeter, Greet)

    val Greeting(message1) = inbox.receive(5.seconds)
    println(s"Greeting: $message1")

    greeter.tell(WhoToGreet("geebi"), ActorRef.noSender)
    inbox.send(greeter, Greet)

    val Greeting(message2) = inbox.receive(5.seconds)
    println(s"Greeting $message2")

    val greetPrinter = system.actorOf(Props[GreetPrinter])
    system.scheduler.schedule(0.seconds, 1.second, greeter, Greet)(system.dispatcher, greetPrinter)

  }

}