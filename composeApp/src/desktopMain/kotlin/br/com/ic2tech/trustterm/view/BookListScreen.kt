package br.com.ic2tech.trustterm.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.ic2tech.trustterm.controller.BookController
import br.com.ic2tech.trustterm.model.Book
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun BookListScreen(controller: BookController) {
    var books by remember { mutableStateOf(emptyList<Book>()) }

    LaunchedEffect(Unit) {
        books = controller.fetchBooks()
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Books", style = MaterialTheme.typography.h4)

        LazyColumn {
            items(books) { book ->
                BookItem(book)
            }
        }
    }
}

@Composable
fun BookItem(book: Book) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(Modifier.padding(16.dp)) {
            Text(book.title, style = MaterialTheme.typography.body1)
            Text("by ${book.author}", style = MaterialTheme.typography.body2)
        }
    }
}