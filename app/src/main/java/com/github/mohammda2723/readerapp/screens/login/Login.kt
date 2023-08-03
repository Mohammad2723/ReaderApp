package com.github.mohammda2723.readerapp.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.github.mohammda2723.readerapp.R
import com.github.mohammda2723.readerapp.component.EmailInput
import com.github.mohammda2723.readerapp.component.PasswordInput
import com.github.mohammda2723.readerapp.component.ReaderLogo
import com.github.mohammda2723.readerapp.navigation.ReaderScreens


//LoginScreen
@Composable
fun Login(navController: NavController, myViewModel: LoginViewModel = viewModel()) {
    val showLoginForm = rememberSaveable {
        mutableStateOf(true)
    }
    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ReaderLogo()

            //USER form
            //FIRST check login or signUp
            val context = LocalContext.current
            if (showLoginForm.value) {
                UserForm(isCreateAccount = false, loading = false) { email, password ->
                    //FB LOGIN

                    myViewModel.logInWithEmailAndPassword(email = email, pass = password, context) {

                        navController.popBackStack()
                        navController.navigate(ReaderScreens.Home.name)
                    }

                }
            } else {
                UserForm(isCreateAccount = true, loading = false) { email, passWord ->


                    myViewModel.CreateUserWithEmailAndPassword(email,passWord){
                        navController.popBackStack()
                        navController.navigate(ReaderScreens.Home.name)
                    }

                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.clickable { showLoginForm.value = !showLoginForm.value },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(text = "New User?: ")
                Text(
                    text = if (showLoginForm.value) "SignUp" else "Login",
                    style = TextStyle(color = MaterialTheme.colorScheme.primary)
                )
            }
        }
    }


}

// User Form
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    loading: Boolean = false,
    isCreateAccount: Boolean = false,
    onDone: (String, String) -> Unit = { _, _ -> }
) {

    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val passwordFocusRequest = remember {
        FocusRequester()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {

        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    Column(
        modifier = Modifier
            .height(500.dp)
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(
                rememberScrollState()
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        // create account notice
        if (isCreateAccount) {
            Text(
                text = stringResource(id = R.string.create_account),
                modifier = Modifier.padding(4.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
        }


        // email
        EmailInput(emailState = email, enabled = !loading, onAction = KeyboardActions {
            passwordFocusRequest.requestFocus()
        }, imeAction = ImeAction.Next)

        Spacer(modifier = Modifier.height(10.dp))
        //passWord
        PasswordInput(
            modifier = Modifier
                .focusRequester(passwordFocusRequest)
                .fillMaxWidth()
                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
            passwordState = password,
            labelId = "Password",
            enabled = !loading,
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions {

                if (!valid) {
                    return@KeyboardActions
                } else {
                    onDone(email.value.trim(), password.value.trim())
                    keyboardController?.hide()
                }

            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        SubmitButton(

            textId = if (isCreateAccount) "Create Account" else "Login",
            loading = loading,
            validInput = valid

        ) {

            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()

        }


    }

}

@Composable
fun SubmitButton(textId: String, loading: Boolean, validInput: Boolean, onClick: () -> Unit) {

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        enabled = !loading && validInput
    ) {

        Text(text = textId)

    }


}
