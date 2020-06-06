package org.juanitodread.rarebooks.actors.protocol

import org.juanitodread.rarebooks.model.Book

import scala.compat.Platform

object RareBooks {
  sealed trait Message {
    def created: Long
  }
  final case class FindBookByTitle(title: String, created: Long = Platform.currentTime) extends Message
  final case class BookFound(book: Book, created: Long = Platform.currentTime) extends Message
  final case class BookNotFound(reason: String, created: Long = Platform.currentTime) extends Message
}
