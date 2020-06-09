package org.juanitodread.rarebooks.actors

import akka.actor.{ Actor, ActorLogging, ActorRef, Props }
import org.juanitodread.rarebooks.actors.protocol.RareBooks.{ BookFound, BookNotFound, FindBookByTitle }
import org.juanitodread.rarebooks.actors.RareBooks.{ Close, Open }

class Customer(rareBooks: ActorRef, title: String) extends Actor with ActorLogging {
  requestBookInfo()

  override def receive: Receive = {
    case bookFound: BookFound =>
      log.info("Book found: {}", bookFound)
    case bookNotFound: BookNotFound =>
      log.info("Book not found. Reason {}", bookNotFound.reason)
  }

  private def requestBookInfo(): Unit = {
    title match {
      case "open" => rareBooks ! Open
      case "close" => rareBooks ! Close
      case _ =>
        log.info("Requesting book info: title={}", title)
        rareBooks ! FindBookByTitle(title)
    }
  }
}

object Customer {
  def props(rareBooks: ActorRef, title: String): Props = {
    Props(new Customer(rareBooks, title))
  }
}
