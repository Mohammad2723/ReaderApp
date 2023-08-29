package com.github.mohammda2723.readerapp.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.github.mohammda2723.readerapp.component.InputField
import com.github.mohammda2723.readerapp.component.SearchTopBar
import com.github.mohammda2723.readerapp.model.MBook


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(navController: NavController , viewModel: SearchViewModel   ) {

    Scaffold(topBar = { SearchTopBar(navController = navController) }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {

            Column(modifier = Modifier.fillMaxSize()) {

//////////////////////////Search Form///////////////////////////////////////////////////////////////

                SearchForm(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) { massage ->
                    //todo:implement search
                    Log.i("search", massage)
                }

                Spacer(modifier = Modifier.height(10.dp))
                BookList(visibel = true)

            }

        }
    }

}

@Composable
fun BookList(visibel: Boolean) {

    val listOfBooks =
        listOf<MBook>(
            MBook(id = "1", title = "A", authors = "mohammad", notes = ""),
            MBook(id = "2", title = "B", authors = "mohammad", notes = ""),
            MBook(id = "3", title = "C", authors = "mohammad", notes = ""),
            MBook(id = "4", title = "F", authors = "mohammad", notes = ""),
            MBook(id = "5", title = "E", authors = "mohammad", notes = ""),
            MBook(id = "6", title = "f", authors = "mohammad", notes = ""),
            MBook(id = "7", title = "g", authors = "mohammad", notes = ""),
        )

    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp)) {
        items(items = listOfBooks) { book ->

            BookRow(book = book)
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    hint: String = "Search",
    onSearch: (String) -> Unit = {}
) {
    val searchQuery = rememberSaveable { mutableStateOf("") }
    val valid =
        remember(searchQuery.value) { mutableStateOf(searchQuery.value.trim().isNotEmpty()) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {

        InputField(

            modifier = modifier,
            valueState = searchQuery,
            labelId = hint,
            enabled = true,
            imeAction = ImeAction.Done,
            onAction = KeyboardActions {
                if (!valid.value) {
                    return@KeyboardActions
                }
                onSearch(searchQuery.value.trim())
                searchQuery.value = ""
                keyboardController?.hide()
            })
    }
}

@Composable
fun BookRow(book: MBook) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 3.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = "https://upload.wikimedia.org/wikipedia/en/d/dc/A_Song_of_Ice_and_Fire_book_collection_box_set_cover.jpg",
                contentDescription = "",
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.width(5.dp))
            Column {
                Text(text = book.title.toString(), style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = book.authors.toString(), style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Date: 2020-09-09", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "[Computer]", style = MaterialTheme.typography.bodyMedium)

            }


        }

    }


}
