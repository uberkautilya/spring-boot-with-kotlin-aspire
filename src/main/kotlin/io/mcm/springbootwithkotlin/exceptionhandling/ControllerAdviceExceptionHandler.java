package io.mcm.springbootwithkotlin.exceptionhandling;

import io.mcm.springbootwithkotlin.exceptionhandling.exception.BooksException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdviceExceptionHandler.class);

    @ExceptionHandler(BooksException.class)
    public ResponseEntity<Object> handleBooksException(BooksException booksException, WebRequest webRequest) {
        LOGGER.info("ControllerAdviceExceptionHandler.handleBooksException: booksException: " + booksException + "| webRequest: " + webRequest);

        Map<String, Object> body = new HashMap<>();
        body.put("Timestamp", LocalDateTime.now());
        body.put("Status", HttpStatus.INTERNAL_SERVER_ERROR);

        if (!CollectionUtils.isEmpty(booksException.getBooksNotCreated())) {
            body.put("Status", HttpStatus.NOT_ACCEPTABLE);
            body.put("Books not created", booksException.getBooksNotCreated());
        }
        if (!CollectionUtils.isEmpty(booksException.getIdsNotDeleted())) {
            body.put("Status", HttpStatus.NOT_FOUND);
            body.put("Id of books not deleted", booksException.getIdsNotDeleted());
        }
        if (!CollectionUtils.isEmpty(booksException.getBooksNotUpdated())) {
            body.put("Status", HttpStatus.NOT_FOUND);
            body.put("Books not updated", booksException.getBooksNotUpdated());
        }
        if (!CollectionUtils.isEmpty(booksException.getIdsNotFound())) {
            body.put("Status", HttpStatus.NOT_FOUND);
            body.put("Ids of books not found", booksException.getIdsNotFound());
        }
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
