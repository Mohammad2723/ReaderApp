package com.github.mohammda2723.readerapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.mohammda2723.readerapp.screens.details.BookDetails
import com.github.mohammda2723.readerapp.screens.home.Home
import com.github.mohammda2723.readerapp.screens.login.Login
import com.github.mohammda2723.readerapp.screens.search.Search
import com.github.mohammda2723.readerapp.screens.spalsh.Splash
import com.github.mohammda2723.readerapp.screens.stats.Stats
import com.github.mohammda2723.readerapp.screens.update.Update

@Composable
 fun ReaderNavigation() {

     val navController = rememberNavController()

    NavHost(navController = navController , startDestination = ReaderScreens.Splash.name){

        //Splash
        composable(ReaderScreens.Splash.name){

            Splash(navController = navController)
        }
        //home
        composable(ReaderScreens.Home.name){
            Home(navController = navController)
        }
        //login
        composable(ReaderScreens.Login.name){

            Login(navController)

        }
        //details
        composable(ReaderScreens.Details.name){
            BookDetails()
        }
        //Search
        composable(ReaderScreens.Search.name){
            Search()
        }
        //Stats
        composable(ReaderScreens.Stats.name){
            Stats()
        }
        //Update
        composable(ReaderScreens.Update.name){
            Update()
        }

    }

}