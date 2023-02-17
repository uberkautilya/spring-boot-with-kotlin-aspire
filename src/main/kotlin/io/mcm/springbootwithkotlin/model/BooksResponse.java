package io.mcm.springbootwithkotlin.model;

import io.mcm.springbootwithkotlin.model.entities.Book;

import java.util.List;

public class BooksResponse {
    List<Book> bookList;
    private int totalPages;
    private long totalElements;
    private int pageNo;

    public BooksResponse(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "BooksResponse{" +
                "bookList=" + bookList +
                '}';
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalCount(long totalElements) {
        this.totalElements = totalElements;
    }

    public void setPageNo(int i) {
        this.pageNo = i;
    }
}
