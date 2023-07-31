package com.github.mohammda2723.readerapp.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/////////////Logo ReaderApp
@Composable
fun ReaderLogo() {
    Text(
        modifier = Modifier.padding(bottom = 16.dp),
        text = "Reader App",
        style = MaterialTheme.typography.headlineLarge,
        color = Color.Red.copy(alpha = 0.5f)
    )
}

//Email input
@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    labelId: String = "Email",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        valueState = emailState,
        labelId = labelId,
        enabled = enabled,
        imeAction = imeAction,
        onAction = onAction,
        keyboardType = KeyboardType.Email
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    onAction: KeyboardActions = KeyboardActions.Default,
    imeAction: ImeAction = ImeAction.Next

) {

    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = {
            Text(
                text = labelId
            )
        },
        singleLine = isSingleLine,
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction


    )
}
//end Email input

//password Input


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    modifier: Modifier,
    passwordState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    passwordVisibility: MutableState<Boolean>,
    onAction: KeyboardActions = KeyboardActions.Default,
    imeAction: ImeAction = ImeAction.Done
) {

    // check passwordVisibility

    val visualTransformation = if (passwordVisibility.value) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    OutlinedTextField(
        value = passwordState.value,
        onValueChange = { passwordState.value = it },
        modifier = modifier,
        label = { Text(text = labelId) },
        singleLine = true,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = visualTransformation,
        trailingIcon = {
            PasswordVisibility(passwordVisibility = passwordVisibility)
        },
        keyboardActions = onAction

    )

}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {

    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible }) {
        Icon(imageVector = Icons.Default.RemoveRedEye, contentDescription = "")

    }

}
