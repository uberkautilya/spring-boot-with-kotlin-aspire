package io.mcm.springbootwithkotlin.repository;

import io.mcm.springbootwithkotlin.model.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    public List<Book> findByPriceAndPublishedDateAndAuthorAndIsIssued(
            @Param("price") Double price,
            @Param("publishedDate") Date publishedDate,
            @Param("author") String author,
            @Param("isIssues") Boolean isIssued);

    public Page<Book> findAll(Specification<Book> specification, Pageable pageable);

    public List<Book> findAll(Specification<Book> specification);
}
