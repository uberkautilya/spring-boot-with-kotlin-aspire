package io.mcm.springbootwithkotlin.model

import io.mcm.springbootwithkotlin.model.entities.Book

class BooksRequest {
    var bookList: List<Book>? = null
    var pageNumber: Int? = null
    var pageSize: Int? = null
    var bookFilter: BookFilter? = null

    override fun toString(): String {
        return "BooksRequest{" +
                "bookList=" + bookList +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", bookFilter=" + bookFilter +
                '}'
    }

    constructor()
    constructor(bookList: List<Book>?) {
        this.bookList = bookList
    }
}