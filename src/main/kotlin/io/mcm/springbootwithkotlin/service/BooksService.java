package io.mcm.springbootwithkotlin.service;

import io.mcm.springbootwithkotlin.exceptionhandling.exception.BooksException;
import io.mcm.springbootwithkotlin.model.BooksRequest;
import io.mcm.springbootwithkotlin.model.BooksResponse;
import io.mcm.springbootwithkotlin.model.entities.Book;
import io.mcm.springbootwithkotlin.repository.BookRepository;
import io.mcm.springbootwithkotlin.specification.BookSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BooksService.class);
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookSpecification bookSpecification;
    @Value("${pagination.page.size.default}")
    private Integer defaultPageSize;

    public BooksResponse save(List<Book> bookList) {
        List<Book> booksAdded = new ArrayList<>();
        try {
            booksAdded = bookRepository.saveAll(bookList);
        } catch (Exception e) {
            LOGGER.info("BooksService.addBooks: exception: " + e.getMessage());
            throw new BooksException(null, null, null, bookList);
        }
        return new BooksResponse(booksAdded);
    }

    public BooksResponse findAll() {
        List<Book> booksFound = bookRepository.findAll();
        return new BooksResponse(booksFound);
    }

    public BooksResponse updateBooks(List<Book> bookList) {
        List<Book> booksUpdated = null;
        try {
            booksUpdated = bookRepository.saveAll(bookList);
        } catch (Exception e) {
            LOGGER.info("BooksService.updateBooks: exception: " + e.getMessage());
            throw new BooksException(null, null, bookList, null);
        }
        return new BooksResponse(booksUpdated);
    }

    public BooksResponse deleteBooks(List<Long> bookIdsToDeleteList) {
        List<Book> deletedBookList = new ArrayList<>();
        for (Long id : bookIdsToDeleteList) {
            Optional<Book> bookOptional = bookRepository.findById(id);
            bookOptional.ifPresent(b -> deletedBookList.add(b));
        }
        try {
            bookRepository.deleteAllById(bookIdsToDeleteList);
        } catch (Exception e) {
            LOGGER.info("BooksService.deleteBooks: exception: " + e.getMessage());
            throw new BooksException(bookIdsToDeleteList, null, null, null);
        }
        return new BooksResponse(deletedBookList);
    }

    public BooksResponse findById(Long id) {
        Optional<Book> bookById = bookRepository.findById(id);
        if (bookById.isEmpty()) {
            throw new BooksException(null, List.of(id), null, null);
        }
        BooksResponse booksResponse = new BooksResponse(List.of(bookById.get()));
        return booksResponse;
    }


    //-------------

    public BooksResponse getBookFiltered(BooksRequest request) {
        List<Book> list = null;
        Page<Book> pages = null;
        if (request.getPageNumber() == null) {
            pages = new PageImpl<>(bookRepository.findAll(bookSpecification.getBooks(request)));
        } else {
            if (request.getPageSize() == null) {
                request.setPageSize(defaultPageSize);
            }
            LOGGER.info("BooksService.getBookFiltered: pageNumber: {}, pageSize: {}", request.getPageNumber(), request.getPageSize());
            Pageable pageable = PageRequest.of(request.getPageNumber() - 1, request.getPageSize());
            pages = bookRepository.findAll(bookSpecification.getBooks(request), pageable);
        }
        if (pages != null && pages.getContent() != null) {
            list = pages.getContent();
            if (list.size() > 0) {
                BooksResponse booksResponse = new BooksResponse(new ArrayList<Book>());
                booksResponse.setTotalPages(pages.getTotalPages());
                booksResponse.setTotalCount(pages.getTotalElements());
                booksResponse.setPageNo(pages.getNumber() + 1);
                for (Book book : list) {
                    booksResponse.getBookList().add(book);
                }
                return booksResponse;
            }
        }
        return null;
    }


}

