package com.github.mohammda2723.readerapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.github.mohammda2723.readerapp.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(navController: NavController) {


    Scaffold(
        topBar = { MyTopAppBar(title = "ReaderApp", navController = navController) },
        floatingActionButton = {
            MyFloatActionButton {
                // todo: onClick FloatingActionButton
            }
        })
    {
        // home screen content
        Column(modifier = Modifier.fillMaxSize() , horizontalAlignment = Alignment.CenterHorizontally) {




        }
    }
}


//region TopAppBar SECTION
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

//LogoutButton
@Composable
fun LogoutButton( onClick: () -> Unit) {
    IconButton(onClick = {
        onClick.invoke()
    }) {
        Icon(
            imageVector = Icons.Rounded.Logout,
            contentDescription = "logout"
        )
    }
}


//profile on topAppBar
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


//FloatingActionButton
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
//endregion TopAppBar SECTION
