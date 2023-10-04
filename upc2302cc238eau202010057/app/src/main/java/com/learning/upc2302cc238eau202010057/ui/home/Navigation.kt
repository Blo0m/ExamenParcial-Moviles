package com.learning.upc2302cc238eau202010057.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learning.upc2302cc238eau202010057.ui.favorites.Favorites
import com.learning.upc2302cc238eau202010057.ui.persons.Persons

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            Home() {
                if( it == "Persons")
                    navController.navigate("${Routes.Persons.route}")
                if( it == "Favorites")
                    navController.navigate("${Routes.Favorites.route}")
            }
        }

        composable(Routes.Persons.route) {
            Persons()
        }

        composable(Routes.Favorites.route) {
            Favorites()
        }
    }
}

sealed class Routes(val route: String) {
    object Home: Routes("Home")
    object Persons: Routes("Persons")
    object Favorites: Routes("Favorites")
}