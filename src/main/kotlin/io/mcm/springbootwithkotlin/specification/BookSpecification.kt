package io.mcm.springbootwithkotlin.specification

import io.mcm.springbootwithkotlin.model.BooksRequest
import io.mcm.springbootwithkotlin.model.entities.Book
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Component
class BookSpecification {
    fun build(request: BooksRequest): Specification<Book?> {
        return Specification { root: Root<Book?>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder ->
            if (Objects.isNull(request) || Objects.isNull(request.bookFilter)) {
                println("The request book parameters are not provided")
                return@Specification criteriaBuilder.and(*arrayOfNulls(0))
            }
            val bookFilter = request.bookFilter
            val predicates: MutableList<Predicate> =
                ArrayList()
            if (Objects.nonNull(bookFilter!!.isIssued)) {
                predicates.add(criteriaBuilder.equal(root.get<Any>("isIssued"), bookFilter.isIssued))
            }
            if (Objects.nonNull(bookFilter.author) && !bookFilter.author!!.isBlank()) {
                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("author")),
                        "%" + bookFilter.author!!.lowercase(Locale.getDefault()) + "%"
                    )
                )
            }
            if (Objects.nonNull(bookFilter.price)) {
                predicates.add(criteriaBuilder.equal(root.get<Any>("price"), bookFilter.price))
            }
            if (Objects.nonNull(bookFilter.orderBy) && !bookFilter.orderBy!!.isBlank()) {
                query.orderBy(criteriaBuilder.desc(root.get<Any>(bookFilter.orderBy)))
            } else {
                query.orderBy(criteriaBuilder.asc(root.get<Any>("isIssued")))
            }
            criteriaBuilder.and(*predicates.toTypedArray())
        }
    }
}