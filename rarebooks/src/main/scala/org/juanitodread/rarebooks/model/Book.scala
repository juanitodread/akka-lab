package org.juanitodread.rarebooks.model

sealed trait Topic
case object Novel extends Topic
case object Horror extends Topic
case object Programming extends Topic
case object Arts extends Topic
case object Report extends Topic
case object Unknown extends Topic

case class Book(
  isbn: String,
  author: String,
  title: String,
  description: String,
  topic: Set[Topic],
  publisher: String,
  year: Int,
  language: String,
  pages: Int)

object Books {
  private val books: Map[String, Book] = Map(
    BooksCatalog.reactiveApplications.isbn -> BooksCatalog.reactiveApplications,
    BooksCatalog.thisIsMonet.isbn -> BooksCatalog.thisIsMonet,
    BooksCatalog.rayuela.isbn -> BooksCatalog.rayuela,
    BooksCatalog.it.isbn -> BooksCatalog.it,
    BooksCatalog.mexicoBarbaro.isbn -> BooksCatalog.mexicoBarbaro)

  def findBookByTitle(title: String): Option[Book] = {
    Thread.sleep(800)
    books.values.find(book => book.title.toLowerCase == title.toLowerCase)
  }
}

private object BooksCatalog {
  val reactiveApplications = Book("9781617292460", "Duncan K. DeVore, Sean Walsh, and Brian Hanafee", "Reactive Application Development", "Reactive Application Development is a hands-on guide that teaches you how to build reliable enterprise applications using reactive design patterns.", Set(Programming), "Manning Publications", 2018, "English", 288)
  val thisIsMonet = Book("9781780676234", "Sara Pappworth", "This is Monet", "Claude Monet is best known as a leader of the Impressionists, his paintings defining the style that triggered a revolution in art.", Set(Arts), "Laurence King Publishing", 2015, "English", 80)
  val rayuela = Book("9788420437484", "Julio Cortázar", "Rayuela", "Rayuela, una de las obras cumbre de la literatura contemporánea en español", Set(Novel), "Real Academia Española", 2019, "Spanish", 700)
  val it = Book("9781982127794", "Stephen King", "It", "About seven adults who return to their hometown to confront a nightmare they had first stumbled on as teenagers... an evil without a name: It.", Set(Novel, Horror), "Scribner", 2019, "English", 1184)
  val mexicoBarbaro = Book("978198212779434", "John Kenneth Turner", "México Bárbaro", "John Kenneth Turner advirtió lúcidamente en el México de Porfirio Díaz el verdadero sentido de la lucha entre una corrupción trágica y una palabra.", Set(Report), "Berbera Editores", 2015, "Spanish", 205)
}
