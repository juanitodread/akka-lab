package org.juanitodread.guidebook

import akka.actor.{ ActorRef, ActorSystem, Props }
import akka.routing.FromConfig

object GuidebookMain {

  def main(args: Array[String]): Unit = {
    val system: ActorSystem = ActorSystem("GuidebookActorSystem")
    val guideProps: Props = Props[Guidebook]
    val routerProps: Props = FromConfig.props(guideProps)
    val guidebook: ActorRef = system.actorOf(routerProps, "guidebook")
  }

}
