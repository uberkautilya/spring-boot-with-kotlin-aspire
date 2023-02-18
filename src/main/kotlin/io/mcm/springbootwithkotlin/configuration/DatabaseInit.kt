package io.mcm.springbootwithkotlin.configuration

import io.mcm.springbootwithkotlin.repository.BooksRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import io.mcm.springbootwithkotlin.model.entities.Book

@Configuration
class DatabaseInit {

    @Bean
    fun databasePopulate(booksRepository: BooksRepository) = ApplicationRunner {
        val dateFormat = SimpleDateFormat("yyyyMMdd")
        val dateCreated = dateFormat.parse("19920706")
        println("dateCreated: $dateCreated")
        val book = Book(
            price= 100.00,
            publishedDate = Date.from(
                LocalDateTime.of(1992, 7, 6, 18, 23, 36)
                    .atZone(ZoneId.systemDefault())
                    .toInstant()),
            author = "Christopher Paolini",
            isIssued = true)
        val savedBook = booksRepository.save(book)
        println("savedBook: $savedBook")
    }
}