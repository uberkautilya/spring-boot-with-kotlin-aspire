package io.mcm.springbootwithkotlin.specification;

import io.mcm.springbootwithkotlin.model.BookFilter;
import io.mcm.springbootwithkotlin.model.BooksRequest;
import io.mcm.springbootwithkotlin.model.entities.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class BookSpecification {

    public Specification<Book> getBooks(BooksRequest request) {
        return (root, query, criteriaBuilder) -> {

            if (Objects.isNull(request) || Objects.isNull(request.getBookFilter())) {
                System.out.println("The request book parameters are not provided");
                return criteriaBuilder.and(new Predicate[0]);
            }
            BookFilter bookFilter = request.getBookFilter();
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(bookFilter.getIsIssued())) {
                predicates.add(criteriaBuilder.equal(root.get("isIssued"), bookFilter.getIsIssued()));
            }
            if (Objects.nonNull(bookFilter.getAuthor()) && !bookFilter.getAuthor().isBlank()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("author")),
                        "%" + bookFilter.getAuthor().toLowerCase() + "%"));
            }
            if (Objects.nonNull(bookFilter.getPrice())) {
                predicates.add(criteriaBuilder.equal(root.get("price"), bookFilter.getPrice()));
            }

            if (Objects.nonNull(bookFilter.getOrderBy()) && !bookFilter.getOrderBy().isBlank()) {
                query.orderBy(criteriaBuilder.desc(root.get(bookFilter.getOrderBy())));
            } else {
                query.orderBy(criteriaBuilder.asc(root.get("isIssued")));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


}