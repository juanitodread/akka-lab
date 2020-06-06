package org.juanitodread.rarebooks

import akka.actor.{ ActorRef, ActorSystem }
import akka.event.Logging
import org.juanitodread.rarebooks.actors.{ Customer, RareBooks }
import org.slf4j.LoggerFactory

import scala.compat.Platform
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io.StdIn

object App {
  private val logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {
    logger.info("App started")

    val system: ActorSystem = ActorSystem("RareBooksSystem")
    logger.info("Actor system initialized: {}", system)
    val app: RareBooksApp = new RareBooksApp(system)
    app.run()
    logger.info("Actor system terminated: {}", system)
  }
}

class RareBooksApp(system: ActorSystem) {
  private val logger = Logging(system, getClass.getName)
  private val rareBooks = system.actorOf(RareBooks.props, "rare-books-actor")

  def run(): Unit = {
    logger.info("Search any book title or enter 'q' to exit")
    commandLoop()
    Await.ready(system.whenTerminated, Duration.Inf)
  }

  @scala.annotation.tailrec
  private def commandLoop(): Unit = {
    StdIn.readLine() match {
      case "q" => system.terminate()
      case query =>
        createCustomer(query)
        commandLoop()
    }
  }

  private def createCustomer(bookTitle: String): ActorRef = {
    system.actorOf(Customer.props(rareBooks, bookTitle), s"customer-actor-${Platform.currentTime}")
  }
}
