package com.github.mohammda2723.readerapp.screens.spalsh

import android.annotation.SuppressLint
import android.view.animation.OvershootInterpolator
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import  androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.ui.draw.scale
import com.github.mohammda2723.readerapp.navigation.ReaderScreens
import kotlinx.coroutines.delay

@SuppressLint("RememberReturnType")
@Preview
@Composable
fun Splash(navController: NavHostController ) {

    // animation
    val scale = remember {
        Animatable(initialValue = 0f)
    }


    LaunchedEffect(key1 = true) {

        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = { OvershootInterpolator(8f).getInterpolation(it) })
        )

        delay(2000L)

        navController.navigate(route = ReaderScreens.Home.name)

    }


    // logo
    Surface(
        border = BorderStroke(1.dp, color = Color.DarkGray),
        modifier = Modifier
            .size(330.dp)

            //animation called in Modifier.scale()
            .scale(scale = scale.value)
            .padding(15.dp),
        shape = CircleShape,

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(1.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Reader App",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Red.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "\" Read. Change . Progress\" ",
                style = MaterialTheme.typography.titleMedium,
                color = Color.LightGray
            )
        }
    }

}