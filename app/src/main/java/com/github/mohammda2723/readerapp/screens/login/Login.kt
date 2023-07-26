package com.github.mohammda2723.readerapp.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.mohammda2723.readerapp.component.ReaderLogo

@Preview
@Composable
fun Login() {


    ReaderLogo()


}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun TextInput() {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    val modifier = Modifier
        .height(250.dp)
        .background(MaterialTheme.colorScheme.background)
        .verticalScroll(
            rememberScrollState()
        )
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

    }

}

//
//@Composable
//fun EmailInput(
//    modifier: Modifier = Modifier,
//    emailState: MutableState<String>,
//    labelId: String = "Email",
//    enabled: Boolean = true,
//    imeAction: ImeAction = ImeAction.Next,
//    onAction: KeyboardActions = KeyboardActions.Default
//) {
//    InputField()
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun InputField(
//    modifier: Modifier = Modifier,
//    valueState: MutableState<String>,
//    labelId: String,
//    enabled: Boolean,
//    isSingleLine: Boolean = true,
//    keyboardType: KeyboardType = KeyboardType.Text,
//    onAction: KeyboardActions = KeyboardActions.Default,
//    imeAction: ImeAction = ImeAction.Next
//
//) {
//
//    OutlinedTextField(
//        value = valueState.value,
//        onValueChange = {
//
//        },
//        label = labelId,
//        singleLine = isSingleLine,
//        modifier = modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp).fillMaxWidth(),
//        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground),
//        enabled = enabled,
//        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction ),
//        keyboardActions = onAction
//
//
//
//    )
//}
