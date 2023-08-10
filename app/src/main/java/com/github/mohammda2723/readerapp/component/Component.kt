package com.github.mohammda2723.readerapp.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.github.mohammda2723.readerapp.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth

//////////////////////////////////Logo ReaderApp///////////////////////////////////////////////////
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


/////////////////////////////////////text Input //////////////////////////////////////////////////
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

////////////////////////////////////////Password Input ////////////////////////////////////////////
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

////////////////////////////////password visibility Button /////////////////////////////////////////
@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {

    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible }) {
        Icon(imageVector = Icons.Default.RemoveRedEye, contentDescription = "")

    }

}

////////////////////////////////////TopAppBar///////////////////////////////////////////////////////
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    showProfile: Boolean = true,
    navController: NavController
) {

    TopAppBar(
        title = {
            Profile(showProfile, title)
        }, actions = {
            if (showProfile) {
                LogoutButton {
                    //  logout fun
                    FirebaseAuth.getInstance().signOut().run {
                        navController.popBackStack()
                        navController.navigate(ReaderScreens.Login.name)
                    }
                }
            }
        })

}

/////////////////////////////////////LogoutButton///////////////////////////////////////////////////
@Composable
fun LogoutButton(onClick: () -> Unit) {
    IconButton(onClick = {
        onClick.invoke()
    }) {
        Icon(
            imageVector = Icons.Rounded.Logout,
            contentDescription = "logout"
        )
    }
}


//////////////////////////////////////////profile on topAppBar/////////////////////////////////////
@Composable
fun Profile(showProfile: Boolean, title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (showProfile) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.primary),
                imageVector = Icons.Default.Person,
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = title, color = MaterialTheme.colorScheme.primary, fontSize = 15.sp)
    }
}

////////////////////////////////////////FloatingActionButton///////////////////////////////////////
@Composable
fun MyFloatActionButton(onClick: () -> Unit) {

//    IconButton(
//        modifier = Modifier
//            .size(50.dp)
//            .clip(CircleShape),
//        colors = IconButtonDefaults.iconButtonColors(
//            containerColor = MaterialTheme.colorScheme.primary,
//            contentColor = MaterialTheme.colorScheme.onPrimary
//        ),
//        onClick = { onClick.invoke() }) {
//        Icon(imageVector = Icons.Default.Add, contentDescription = "")
//    }

    FloatingActionButton(
        onClick = { onClick.invoke() },
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {

        Icon(imageVector = Icons.Default.Add, contentDescription = "")

    }


}

/////////////////////////////////Reading Area /////////////////////////////////////////////////////
@Composable
fun ReadingNow(navController: NavController) {

    val name = FirebaseAuth.getInstance()
        .currentUser?.email?.split('@')?.get(0) ?: "N/A"


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        TitleSection(label = "You reading \n Activity Right now")

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .clickable { navController.navigate(ReaderScreens.Stats.name) }
                    .size(45.dp),
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                modifier = Modifier.padding(2.dp),
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Clip
            )
        }
    }
}


////////////////////////////Title Section /////////////////////////////////////////////////////////
@Composable
fun TitleSection(
    modifier: Modifier = Modifier,
    label: String = "Studying: \n Song Of Ice and Fire"
) {

    Surface(modifier = modifier.padding(start = 5.dp, top = 1.dp)) {

        Column {
            Text(
                text = label,
                fontSize = 19.sp,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Left
            )

        }

    }


}