package com.github.mohammda2723.readerapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.mohammda2723.readerapp.component.MyFloatActionButton
import com.github.mohammda2723.readerapp.component.MyTopAppBar
import com.github.mohammda2723.readerapp.component.ReadingNow


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

        ReadingNow(navController = navController)

    }
}

