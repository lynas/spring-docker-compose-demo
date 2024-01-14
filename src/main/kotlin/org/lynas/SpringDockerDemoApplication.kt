package org.lynas

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@SpringBootApplication
class SpringDockerDemoApplication

fun main(args: Array<String>) {
    runApplication<SpringDockerDemoApplication>(*args)
}

@Entity
class Book {

    @Id
    var id: UUID = UUID.randomUUID()

    @Column
    var name: String = ""
}

interface BookRepository : JpaRepository<Book, UUID>

@RestController
@RequestMapping("/books")
class BookController(
    val bookRepository: BookRepository
) {

    @PostMapping
    fun saveNewBook(@RequestBody book: Book): Book = bookRepository.save(book)

    @GetMapping
    fun getAllBooks(): MutableList<Book> = bookRepository.findAll()
}