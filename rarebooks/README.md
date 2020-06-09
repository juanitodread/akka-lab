# rarebooks

## About
Simple Actor system to represent a library where _customers_ can perform a query to get information about books.

There are three different actors:

### RareBooks actor
This actor is a top-level actor that represents the business, Rare Books as follows:

1) Uses a single **Librarian** actor who is responsible for customer requests.
    * Process a `Message`:
      * Forward the request to the `Librarian`

### Customer actor
This actor represents the **Customer** and is a top-level actor.

1) Has a single behavior of receiving messages
1) Process a `BookFound` message.
1) Process a `BookNotFound` message.

### Librarian actor
This actor is a child of **RareBooks** actor that processes all customer requests as follows:

1) Process a `FindBookByTitle` command:
    * Performs a _search_ in the in-memory DB.

## How to run
1) Run `sbt run`
1) Enter query to search a book by title. Every query is a new Customer Actor.
1) Exit with query=q
