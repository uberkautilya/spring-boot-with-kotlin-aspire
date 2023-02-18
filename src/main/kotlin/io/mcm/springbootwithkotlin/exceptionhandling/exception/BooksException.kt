package io.mcm.springbootwithkotlin.exceptionhandling.exception

import io.mcm.springbootwithkotlin.model.entities.Book

class BooksException(
    var idsNotDeleted: List<Long>,
    var idsNotFound: List<Long>,
    var booksNotUpdated: List<Book?>,
    var booksNotCreated: List<Book?>
) : RuntimeException() {

    override fun toString(): String {
        return "BooksException{" +
                "idsNotDeleted=" + idsNotDeleted +
                ", idsNotFound=" + idsNotFound +
                ", booksNotUpdated=" + booksNotUpdated +
                ", booksNotCreated=" + booksNotCreated +
                '}'
    }
}