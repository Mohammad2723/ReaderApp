package com.github.mohammda2723.readerapp.navigation

import java.lang.IllegalArgumentException

enum class ReaderScreens {

    Splash,
    Login,
    CreteAccount,
    Home,
    Search,
    Details,
    Update,
    Stats;


    companion object {

        fun fromRoute(Route:String):ReaderScreens = when(Route.substringBefore("/")){
            Splash.name -> Splash
            Login.name -> Login
            CreteAccount.name -> CreteAccount
            Home.name -> Home
            Search.name -> Search
            Details.name -> Details
            Update.name -> Update
            Stats.name -> Stats
            else -> throw IllegalArgumentException(" Route $Route is not Recognized")

        }

    }

}