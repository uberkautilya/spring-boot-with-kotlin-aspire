package io.mcm.springbootwithkotlin.controller;

import io.mcm.springbootwithkotlin.model.BooksRequest
import io.mcm.springbootwithkotlin.model.BooksResponse
import io.mcm.springbootwithkotlin.service.BooksService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/books")
public class BooksController(val booksService: BooksService) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(BooksController::class.java)
    }

    @PostMapping
    fun addBooks(@RequestBody booksRequest: BooksRequest): ResponseEntity<BooksResponse> {
        LOGGER.info("BooksController:: addBooks({})", booksRequest)
        val booksResponse: BooksResponse = booksService.save(booksRequest.bookList)
        return ResponseEntity.ok(booksResponse)
    }

    @PostMapping("/filtered")
    fun getBookFiltered(@RequestBody booksRequest: BooksRequest): ResponseEntity<BooksResponse> {
        LOGGER.info("BooksController.getBookFiltered: booksRequest: $booksRequest")
        val booksResponse: BooksResponse = booksService.getBookFiltered(booksRequest)
        return ResponseEntity.ok(booksResponse)
    }

    @GetMapping
    fun findAll(): ResponseEntity<BooksResponse> {
        LOGGER.info("BooksController:: findAll() method")
        val booksFound:BooksResponse = booksService.findAll()
        return ResponseEntity<BooksResponse>(booksFound, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<BooksResponse> {
        LOGGER.info("BooksController:: findById({})", id)
        val bookById: BooksResponse = booksService.findById(id)
        return ResponseEntity<BooksResponse>(bookById, HttpStatus.OK)
    }

    @PutMapping
    fun updateBooks(@RequestBody booksRequest: BooksRequest): ResponseEntity<BooksResponse> {
        LOGGER.info("BooksController:: updateBooks({})", booksRequest)
        val updatedBooksResponse: BooksResponse = booksService.updateBooks(booksRequest.bookList)
        return ResponseEntity<BooksResponse>(updatedBooksResponse, HttpStatus.OK)
    }

    @DeleteMapping
    fun deleteBooks(@RequestBody bookIdsToDelete: List<Long>): ResponseEntity<BooksResponse> {
        LOGGER.info("BooksController:: deleteBooks({})", bookIdsToDelete)
        val booksDeletedResponse: BooksResponse = booksService.deleteBooks(bookIdsToDelete)
        return ResponseEntity<BooksResponse>(booksDeletedResponse, HttpStatus.OK)
    }

    @GetMapping("/property")
    fun getApplicationProperty(): ResponseEntity<String> {
        LOGGER.info("BooksController:: getApplicationProperty()")
        val applicationProperty: String = booksService.getApplicationProperty()
        return ResponseEntity<String>(applicationProperty, HttpStatus.OK)
    }
}
