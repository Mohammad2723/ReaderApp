package com.github.mohammda2723.readerapp.screens.home

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.mohammda2723.readerapp.component.ListCard
import com.github.mohammda2723.readerapp.component.MyFloatActionButton
import com.github.mohammda2723.readerapp.component.MyTopAppBar
import com.github.mohammda2723.readerapp.component.Header
import com.github.mohammda2723.readerapp.component.TitleSection
import com.github.mohammda2723.readerapp.model.MBook
import com.github.mohammda2723.readerapp.navigation.ReaderScreens


@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(navController: NavController) {


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

    Scaffold(
        topBar = { MyTopAppBar(title = "ReaderApp", navController = navController) },
        floatingActionButton = {
            MyFloatActionButton {
                navController.navigate(ReaderScreens.Search.name)
            }
        })

    { padding ->
        // home screen content
        FBContent(
            listOfBooks = listOfBooks,
            modifier = Modifier.padding(padding),
            navController = navController
        )
    }
}


@Composable
fun FBContent(modifier: Modifier, navController: NavController, listOfBooks: List<MBook>) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Header(navController = navController)
        Spacer(modifier = Modifier.height(10.dp))
        ListCard()
        Spacer(modifier = Modifier.height(10.dp))
        ReadingRightNowAria(book = listOfBooks, navController = navController)

    }
}


@Composable
fun ReadingRightNowAria(book: List<MBook> = emptyList(), navController: NavController) {
    TitleSection(label = "Reading List")
    BookListArea(listOfBook = book, navController = navController)

}

@Composable
fun BookListArea(listOfBook: List<MBook>, navController: NavController) {

    HorizontalScrollableComponent(listOfBook = listOfBook) {
        Log.i("Card", " press on $it")
    }


}

@Composable
fun HorizontalScrollableComponent(listOfBook: List<MBook>, onCardPressed: (String) -> Unit) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(280.dp)
            .horizontalScroll(scrollState)
            .fillMaxWidth()
            .height(280.dp)
    ) {
        for (book in listOfBook) {

            ListCard(book = book) {
                onCardPressed(it)
            }

        }

    }

}


//////////////////////////////OWN CODE/////////////////////////////////////////////////
//    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {


//        Card(
//            modifier = Modifier
//                .fillMaxWidth(0.8F)
//                .height(300.dp)
//                .padding(16.dp)
//                .clickable { onPressDetails.invoke(book.title.toString()) },
//            shape = RoundedCornerShape(15.dp),
////            colors = CardDefaults.cardColors(
////                contentColor = MaterialTheme.colorScheme.onPrimary,
////                containerColor = MaterialTheme.colorScheme.primary
////            )
//            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
//        ) {
//            Box(modifier = Modifier.fillMaxSize()) {
//
//                Image(
//                    rememberAsyncImagePainter(model = "https://upload.wikimedia.org/wikipedia/en/5/5d/A_Dance_With_Dragons_US.jpg"),
//                    contentDescription = "",
//                    modifier = Modifier.fillMaxSize(),
//                    contentScale = ContentScale.Crop
//                )
//
//                Column(
//                    Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.SpaceBetween,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    // star row
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(7.dp),
//                        Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//
//                        StarRank()
//                        FavButton()
//
//
//                    }
//
//
//                   // name of book
//                    Column(
//                        modifier = Modifier
//                            .height(40.dp)
//                            .fillMaxWidth()
//                            .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)),
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//
//                         Text(text = "A Song Of Ice And Fire", fontStyle = MaterialTheme.typography.bodyLarge.fontStyle, color = MaterialTheme.colorScheme.onPrimary)
//                    }
//
//                }
//            }
//        }
//    }



