package io.mcm.springbootwithkotlin.model.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "books")
open class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var price: Double? = null
    var publishedDate: Date? = null
    var author: String? = null
    var isIssued: Boolean? = null

    constructor()
    constructor(
        id: Long? = null,
        price: Double? = null,
        publishedDate: Date? = null,
        author: String? = null,
        isIssued: Boolean? = null
    ) {
        this.id = id
        this.price = price
        this.publishedDate = publishedDate
        this.author = author
        this.isIssued = isIssued
    }

    override fun toString(): String {
        return "Book{id=$id, price=$price, publishedDate=$publishedDate, author='$author', issued=$isIssued}"
    }
}