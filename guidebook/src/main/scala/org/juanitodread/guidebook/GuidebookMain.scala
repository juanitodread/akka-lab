package org.juanitodread.guidebook

import akka.actor.{ ActorRef, ActorSystem, Props }

object GuidebookMain {

  def main(args: Array[String]): Unit = {
    val system: ActorSystem = ActorSystem("GuidebookActorSystem")
    val guideProps: Props = Props[Guidebook]
    val guidebook: ActorRef = system.actorOf(guideProps, "GuidebookActor")
  }

}
