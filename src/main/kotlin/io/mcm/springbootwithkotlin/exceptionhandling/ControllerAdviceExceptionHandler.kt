package io.mcm.springbootwithkotlin.exceptionhandling

import io.mcm.springbootwithkotlin.exceptionhandling.exception.BooksException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.CollectionUtils
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class ControllerAdviceExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(BooksException::class)
    fun handleBooksException(booksException: BooksException, webRequest: WebRequest): ResponseEntity<Any> {
        LOGGER.info("ControllerAdviceExceptionHandler.handleBooksException: booksException: $booksException| webRequest: $webRequest")
        val body: MutableMap<String, Any> = HashMap()
        body["Timestamp"] = LocalDateTime.now()
        body["Status"] = HttpStatus.INTERNAL_SERVER_ERROR
        if (!CollectionUtils.isEmpty(booksException.booksNotCreated)) {
            body["Status"] = HttpStatus.NOT_ACCEPTABLE
            body["Books not created"] = booksException.booksNotCreated
        }
        if (!CollectionUtils.isEmpty(booksException.idsNotDeleted)) {
            body["Status"] = HttpStatus.NOT_FOUND
            body["Id of books not deleted"] = booksException.idsNotDeleted
        }
        if (!CollectionUtils.isEmpty(booksException.booksNotUpdated)) {
            body["Status"] = HttpStatus.NOT_FOUND
            body["Books not updated"] = booksException.booksNotUpdated
        }
        if (!CollectionUtils.isEmpty(booksException.idsNotFound)) {
            body["Status"] = HttpStatus.NOT_FOUND
            body["Ids of books not found"] = booksException.idsNotFound
        }
        return ResponseEntity(body, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(ControllerAdviceExceptionHandler::class.java)
    }
}