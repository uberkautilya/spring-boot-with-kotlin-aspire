package io.mcm.springbootwithkotlin.model;

import io.mcm.springbootwithkotlin.model.entities.Book;

public class BookFilter extends Book {
    String orderBy;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
