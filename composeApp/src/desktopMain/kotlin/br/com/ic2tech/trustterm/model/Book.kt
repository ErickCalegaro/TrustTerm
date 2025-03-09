package br.com.ic2tech.trustterm.model

data class Book(
    val id: Int,
    val title: String,
    val author: String
)

object BookRepository {
    private val books = mutableListOf(
        Book(1, "Kotlin for Beginners", "John Doe"),
        Book(2, "Advanced Kotlin", "Jane Smith")
    )

    fun getBooks(): List<Book> = books

    fun addBook(book: Book) {
        books.add(book)
    }
}