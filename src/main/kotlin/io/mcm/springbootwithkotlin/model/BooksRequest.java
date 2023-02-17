package io.mcm.springbootwithkotlin.model;

import io.mcm.springbootwithkotlin.model.entities.Book;

import java.util.List;

public class BooksRequest {
    List<Book> bookList;
    Integer pageNumber;
    Integer pageSize;
    BookFilter bookFilter;

    @Override
    public String toString() {
        return "BooksRequest{" +
                "bookList=" + bookList +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", bookFilter=" + bookFilter +
                '}';
    }

    public BooksRequest() {
    }

    public BooksRequest(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public BookFilter getBookFilter() {
        return bookFilter;
    }

    public void setBookFilter(BookFilter bookFilter) {
        this.bookFilter = bookFilter;
    }
}
