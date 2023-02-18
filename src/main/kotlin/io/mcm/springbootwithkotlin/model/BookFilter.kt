package io.mcm.springbootwithkotlin.model

import io.mcm.springbootwithkotlin.model.entities.Book

class BookFilter : Book() {
    var orderBy: String? = null
}