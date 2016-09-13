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
package org.juanitodread.helloworld.actor

import akka.actor.Actor
import org.juanitodread.helloworld.model.WhoToGreet
import org.juanitodread.helloworld.model.Greeting
import org.juanitodread.helloworld.model.Greet

class Greeter extends Actor {
  var greeting = ""

  def receive = {
    case WhoToGreet(who) => greeting = s"Hello $who!"
    case Greet => sender ! Greeting(greeting)
  }
}

class GreetPrinter extends Actor {
  def receive = {
    case Greeting(message) => println(message)
  }
}