package io.mcm.springbootwithkotlin.model

import io.mcm.springbootwithkotlin.model.entities.Book

class BooksResponse(var bookList: MutableList<Book?>) {
    private var totalPages = 0
    private var totalElements: Long = 0
    private var pageNo = 0
    override fun toString(): String {
        return "BooksResponse{" +
                "bookList=" + bookList +
                '}'
    }

    fun setTotalPages(totalPages: Int) {
        this.totalPages = totalPages
    }

    fun setTotalCount(totalElements: Long) {
        this.totalElements = totalElements
    }

    fun setPageNo(i: Int) {
        pageNo = i
    }
}