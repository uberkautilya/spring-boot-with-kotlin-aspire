package io.mcm.springbootwithkotlin.controller;

import io.mcm.springbootwithkotlin.model.BooksRequest;
import io.mcm.springbootwithkotlin.model.BooksResponse;
import io.mcm.springbootwithkotlin.service.BooksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/books")
public class BooksController {
    @Autowired
    BooksService booksService;
    private static final Logger LOGGER = LoggerFactory.getLogger(BooksController.class);

    @PostMapping
    public ResponseEntity<BooksResponse> addBooks(@RequestBody BooksRequest booksRequest) {
        LOGGER.info("BooksController:: addBooks({})", booksRequest);
        BooksResponse booksResponse = booksService.save(booksRequest.getBookList());
        return ResponseEntity.ok(booksResponse);
    }

    @PostMapping("/filtered")
    public ResponseEntity<BooksResponse> getBookFiltered(@RequestBody BooksRequest booksRequest) {
        LOGGER.info("BooksController.getBookFiltered: booksRequest: " + booksRequest);
        BooksResponse booksResponse = booksService.getBookFiltered(booksRequest);
        return ResponseEntity.ok(booksResponse);
    }

    @GetMapping
    public ResponseEntity<BooksResponse> findAll() {
        LOGGER.info("BooksController:: findAll() method");
        BooksResponse booksFound = booksService.findAll();
        return new ResponseEntity<>(booksFound, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BooksResponse> findById(@PathVariable("id") Long id) {
        LOGGER.info("BooksController:: findById({})", id);
        BooksResponse bookById = booksService.findById(id);
        return new ResponseEntity<>(bookById, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BooksResponse> updateBooks(@RequestBody BooksRequest booksRequest) {
        LOGGER.info("BooksController:: updateBooks({})", booksRequest);
        BooksResponse updatedBooksResponse = booksService.updateBooks(booksRequest.getBookList());
        return new ResponseEntity<>(updatedBooksResponse, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<BooksResponse> deleteBooks(@RequestBody List<Long> bookIdsToDelete) {
        LOGGER.info("BooksController:: deleteBooks({})", bookIdsToDelete);
        BooksResponse booksDeletedResponse = booksService.deleteBooks(bookIdsToDelete);
        return new ResponseEntity<>(booksDeletedResponse, HttpStatus.OK);
    }

}
