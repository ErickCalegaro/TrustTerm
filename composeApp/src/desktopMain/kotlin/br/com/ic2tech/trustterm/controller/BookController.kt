package br.com.ic2tech.trustterm.controller

import br.com.ic2tech.trustterm.model.Book
import br.com.ic2tech.trustterm.model.BookRepository

class BookController {
    fun fetchBooks(): List<Book> {
        return BookRepository.getBooks()
    }

    fun addNewBook(title: String, author: String) {
        val newBook = Book(BookRepository.getBooks().size + 1, title, author)
        BookRepository.addBook(newBook)
    }
}