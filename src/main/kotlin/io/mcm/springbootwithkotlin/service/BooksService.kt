package io.mcm.springbootwithkotlin.service

import io.mcm.springbootwithkotlin.exceptionhandling.exception.BooksException
import io.mcm.springbootwithkotlin.model.BooksRequest
import io.mcm.springbootwithkotlin.model.BooksResponse
import io.mcm.springbootwithkotlin.model.entities.Book
import io.mcm.springbootwithkotlin.repository.BooksRepository
import io.mcm.springbootwithkotlin.specification.BookSpecification
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BooksService(private val booksRepository: BooksRepository) {
    @Autowired
    private val bookSpecification: BookSpecification? = null

    @Value("\${pagination.page.size.default}")
    private val defaultPageSize: Int? = null

    @Value("\${custom.application.properties.one: DefaultValue}")
    private lateinit var applicationProperty: String

    companion object {
        private val LOGGER = LoggerFactory.getLogger(BooksService::class.java)
    }

    /**
     * An example of fetching an application property provided in application.yml file
     */
    fun getApplicationProperty(): String {
        return applicationProperty
    }
    /**
     * Save a list of books passed as request body
     */
    fun save(bookList: List<Book?>): BooksResponse {
        var booksAdded: MutableList<Book?> = try {
            booksRepository.saveAll(bookList)
        } catch (e: Exception) {
            LOGGER.info("BooksService.addBooks: exception: " + e.message)
            throw BooksException(emptyList(), emptyList(), emptyList(), bookList)
        }
        return BooksResponse(booksAdded)
    }

    /**
     * Fetch all the books saved in the database
     */
    fun findAll(): BooksResponse {
        val booksFound = booksRepository.findAll()
        return BooksResponse(booksFound)
    }

    /**
     * Update books using their Ids
     */
    fun updateBooks(bookList: List<Book?>): BooksResponse {
        var booksUpdated: MutableList<Book?> = try {
            booksRepository.saveAll(bookList)
        } catch (e: Exception) {
            LOGGER.info("BooksService.updateBooks: exception: " + e.message)
            throw BooksException(emptyList(), emptyList(), bookList, emptyList())
        }
        return BooksResponse(booksUpdated)
    }

    /**
     * Delete Books by Id
     */
    fun deleteBooks(bookIdsToDeleteList: List<Long>): BooksResponse {
        val deletedBookList: MutableList<Book?> = ArrayList()
        for (id in bookIdsToDeleteList) {
            val bookOptional = booksRepository.findById(id)
            bookOptional.ifPresent { b: Book ->
                deletedBookList.add(
                    b
                )
            }
        }
        try {
            booksRepository.deleteAllById(bookIdsToDeleteList)
        } catch (e: Exception) {
            LOGGER.info("BooksService.deleteBooks: exception: " + e.message)
            throw BooksException(bookIdsToDeleteList, emptyList(), emptyList(), emptyList())
        }
        return BooksResponse(deletedBookList)
    }

    /**
     * Find book by Id
     */
    fun findById(id: Long): BooksResponse {
        val bookById =
            booksRepository.findById(id)
        if (bookById.isEmpty) {
            throw BooksException(emptyList(), listOf(id), emptyList(), emptyList())
        }
        return BooksResponse(mutableListOf(bookById.get()))
    }

    /**
     * Find books with multiple filters using Specification and criteriaBuilder
     */
    fun getBookFiltered(request: BooksRequest): BooksResponse {
        var list: List<Book?>?
        var pages: Page<Book?>?
        if (request.pageNumber == null) {
            pages = PageImpl(booksRepository.findAll(bookSpecification!!.build(request)))
        } else {
            if (request.pageSize == null) {
                request.pageSize = defaultPageSize
            }
            LOGGER.info(
                "BooksService.getBookFiltered: pageNumber: {}, pageSize: {}",
                request.pageNumber,
                request.pageSize
            )
            val pageable: Pageable = PageRequest.of(request.pageNumber!! - 1, request.pageSize!!)
            pages = booksRepository.findAll(bookSpecification!!.build(request), pageable)
        }
        list = pages.content
        LOGGER.info("pages.content: ${pages.content}")
        if (list.isNotEmpty()) {
            val booksResponse = BooksResponse(mutableListOf())
            booksResponse.setTotalPages(pages.totalPages)
            booksResponse.setTotalCount(pages.totalElements)
            booksResponse.setPageNo(pages.number + 1)
            for (book in list) {
                booksResponse.bookList.add(book)
            }
            return booksResponse
        }
        return BooksResponse(mutableListOf())
    }
}