package com.github.mohammda2723.readerapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.github.mohammda2723.readerapp.component.MyFloatActionButton
import com.github.mohammda2723.readerapp.component.MyTopAppBar
import com.github.mohammda2723.readerapp.component.ReadingNow
import com.github.mohammda2723.readerapp.model.MBook


@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(navController: NavController) {


    Scaffold(
        topBar = { MyTopAppBar(title = "ReaderApp", navController = navController) },
        floatingActionButton = {
            MyFloatActionButton {
                // todo: onClick FloatingActionButton
            }
        })

    { padding ->
        // home screen content
        HomeContent(modifier = Modifier.padding(padding), navController = navController)
    }
}


@Composable
fun HomeContent(modifier: Modifier, navController: NavController) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        ReadingNow(navController = navController)
        Spacer(modifier = Modifier.height(10.dp))
        BigListCard()

    }
}

@Preview
@Composable
fun BigListCard(
    book: MBook = MBook(
        id = "aff",
        title = "A Song of Ice and Fire",
        authors = "George R. R. Martin",
        notes = "A Song of Ice and Fire (commonly abbreviated as ASoIaF) is an ongoing series of epic fantasy novels by American novelist and screenwriter George R. R. Martin. Martin began writing the series in 1991 and the first volume was published in 1996. Originally planned as a trilogy, the series now consists of five published volumes; a further two are planned. In addition there are three prequel novellas currently available, with several more being planned, and a series of novella-length excerpts from the main Ice and Fire novels."
    ), onPressDetails: (String) -> Unit = { }
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {


//        val context = LocalContext.current
//        val resources = context.resources
//
//        val displayMetrics = resources.displayMetrics
//        val screenWith = displayMetrics.widthPixels / displayMetrics.density

        Card(
            modifier = Modifier
                .fillMaxWidth(0.8F)
                .height(300.dp)
                .padding(16.dp)
                .clickable { onPressDetails.invoke(book.title.toString()) },
            shape = RoundedCornerShape(15.dp),
//            colors = CardDefaults.cardColors(
//                contentColor = MaterialTheme.colorScheme.onPrimary,
//                containerColor = MaterialTheme.colorScheme.primary
//            )
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                Image(
                    rememberAsyncImagePainter(model = "https://upload.wikimedia.org/wikipedia/en/5/5d/A_Dance_With_Dragons_US.jpg"),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(7.dp),
                        Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            modifier = Modifier.background(shape = CircleShape, color = MaterialTheme.colorScheme.primary),
                            onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }


                }
            }

        }
    }

}
