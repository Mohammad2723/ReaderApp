package com.github.mohammda2723.readerapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.github.mohammda2723.readerapp.model.MBook
import com.github.mohammda2723.readerapp.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth


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


@Composable
fun HomeContent(modifier: Modifier, navController: NavController) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        ReadingRightNowArea(navController = navController)

    }
}

@Composable
fun ReadingRightNowArea(books: List<MBook> = emptyList(), navController: NavController) {

    val name = FirebaseAuth.getInstance()
        .currentUser?.email?.split('@')?.get(0) ?: "N/A"


    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

        TitleSection(label = "You reading \n Activity Right now")

        Column(verticalArrangement = Arrangement.Center) {
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
