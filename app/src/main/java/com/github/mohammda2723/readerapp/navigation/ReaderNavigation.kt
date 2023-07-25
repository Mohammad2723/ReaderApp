package com.github.mohammda2723.readerapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
 fun ReaderNavigation() {

     val navController = rememberNavController()

    NavHost(navController = navController , startDestination = ReaderScreens.Splash.name){

        //Splash
        composable(ReaderScreens.Splash.name){

        }
        //home
        composable(ReaderScreens.Home.name){

        }
        //login
        composable(ReaderScreens.Login.name){

        }
        //details
        composable(ReaderScreens.Details.name){

        }
        //Search
        composable(ReaderScreens.Search.name){

        }
        //Stats
        composable(ReaderScreens.Stats.name){

        }
        //Update
        composable(ReaderScreens.Update.name){

        }

    }

}