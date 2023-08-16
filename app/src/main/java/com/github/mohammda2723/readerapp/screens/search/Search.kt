package com.github.mohammda2723.readerapp.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.mohammda2723.readerapp.component.InputField
import com.github.mohammda2723.readerapp.component.SearchTopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(navController: NavController) {

    Scaffold(topBar = { SearchTopBar(navController = navController) }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {

            Column(modifier = Modifier.fillMaxSize()) {
                SerachForme(
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ){

                }
            }

        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SerachForme(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    hint: String = "Search",
    onSerach: (String) -> Unit = {}
) {
    Column() {
        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState) {
            searchQueryState.value.trim().isNotEmpty()
        }


        InputField(
            valueState = searchQueryState,
            labelId = "Search",
            enabled = true,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onSerach(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            })

    }

}


