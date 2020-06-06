package org.juanitodread.rarebooks.actors

import akka.actor.{ Actor, ActorLogging, Props }
import org.juanitodread.rarebooks.actors.protocol.RareBooks.{ BookFound, BookNotFound, FindBookByTitle }
import org.juanitodread.rarebooks.model.Books

class Librarian extends Actor with ActorLogging {
  override def receive: Receive = {
    case bookByTitle: FindBookByTitle =>
      log.info("Message received: {}", bookByTitle)
      Books.findBookByTitle(bookByTitle.title) match {
        case Some(book) =>
          sender ! BookFound(book)
        case None =>
          sender ! BookNotFound(s"The title='${bookByTitle.title}' you are looking for is out of stock")
      }
  }
}

object Librarian {
  def props: Props = Props(new Librarian)
}
