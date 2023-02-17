package io.mcm.springbootwithkotlin.repository

import io.mcm.springbootwithkotlin.model.entities.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.query.Param
import java.util.*

interface BooksRepository : JpaRepository<Book?, Long?>, JpaSpecificationExecutor<Book?> {
    fun findByPriceAndPublishedDateAndAuthorAndIsIssued(
        @Param("price") price: Double?,
        @Param("publishedDate") publishedDate: Date?,
        @Param("author") author: String?,
        @Param("isIssues") isIssued: Boolean?
    ): List<Book?>?

    override fun findAll(specification: Specification<Book?>?, pageable: Pageable): Page<Book?>
    override fun findAll(specification: Specification<Book?>?): List<Book?>
}