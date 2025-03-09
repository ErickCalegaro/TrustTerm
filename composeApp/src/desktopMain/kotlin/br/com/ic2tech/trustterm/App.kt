package br.com.ic2tech.trustterm

import androidx.compose.runtime.*
import br.com.ic2tech.trustterm.controller.BookController
import br.com.ic2tech.trustterm.model.ManagerXML
import br.com.ic2tech.trustterm.view.BookListScreen

@Composable
fun App() {

    try {
        val cXML = ManagerXML()
        cXML.main()
    }catch (e: Exception){
        e.printStackTrace()
    }

    val controller = BookController()
    BookListScreen(controller)

}