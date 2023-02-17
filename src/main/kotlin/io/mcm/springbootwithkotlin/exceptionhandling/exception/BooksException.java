package io.mcm.springbootwithkotlin.exceptionhandling.exception;

import io.mcm.springbootwithkotlin.model.entities.Book;

import java.util.List;

public class BooksException extends RuntimeException {
    List<Long> idsNotDeleted;
    List<Long> idsNotFound;
    List<Book> booksNotUpdated;

    public BooksException(List<Long> idsNotDeleted, List<Long> idsNotFound, List<Book> booksNotUpdated, List<Book> booksNotCreated) {
        this.idsNotDeleted = idsNotDeleted;
        this.idsNotFound = idsNotFound;
        this.booksNotUpdated = booksNotUpdated;
        this.booksNotCreated = booksNotCreated;
    }

    List<Book> booksNotCreated;

    @Override
    public String toString() {
        return "BooksException{" +
                "idsNotDeleted=" + idsNotDeleted +
                ", idsNotFound=" + idsNotFound +
                ", booksNotUpdated=" + booksNotUpdated +
                ", booksNotCreated=" + booksNotCreated +
                '}';
    }

    public List<Long> getIdsNotDeleted() {
        return idsNotDeleted;
    }

    public void setIdsNotDeleted(List<Long> idsNotDeleted) {
        this.idsNotDeleted = idsNotDeleted;
    }

    public List<Long> getIdsNotFound() {
        return idsNotFound;
    }

    public void setIdsNotFound(List<Long> idsNotFound) {
        this.idsNotFound = idsNotFound;
    }

    public List<Book> getBooksNotUpdated() {
        return booksNotUpdated;
    }

    public void setBooksNotUpdated(List<Book> booksNotUpdated) {
        this.booksNotUpdated = booksNotUpdated;
    }

    public List<Book> getBooksNotCreated() {
        return booksNotCreated;
    }

    public void setBooksNotCreated(List<Book> booksNotCreated) {
        this.booksNotCreated = booksNotCreated;
    }
}
