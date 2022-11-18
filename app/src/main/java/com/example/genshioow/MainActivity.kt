package com.example.genshioow

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.genshioow.features.presentation.add_edit.*
import com.example.genshioow.features.presentation.util.Screens
import com.example.genshioow.navigation.BottomNavItem
import com.example.genshioow.navigation.BottomNavigationBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        items = listOf(
                            BottomNavItem(
                                name = "Characters",
                                route = Screens.CharactersScreen.route,
                                icon = painterResource(id = R.drawable.ic_baseline_people_24)
                            ),
                            BottomNavItem(
                                name = "Weapons",
                                route = Screens.WeaponsScreen.route,
                                icon = painterResource(id = R.drawable.sword)
                            ),
                            BottomNavItem(
                                name = "Home",
                                route = Screens.HomeScreen.route,
                                icon = painterResource(id = R.drawable.ic_baseline_home_24)
                            ),
                            BottomNavItem(
                                name = "Artifacts",
                                route = Screens.ArtifactsScreen.route,
                                icon = painterResource(id = R.drawable.ic_baseline_grade_24)
                            ),
                            BottomNavItem(
                                name = "Map",
                                route = Screens.MapScreen.route,
                                icon = painterResource(id = R.drawable.map)
                            )
                        ),
                        navController = navController,
                        onItemClick = {
                            navController.navigate(it.route)
                        }
                    )
                }
            ) { padding ->
                Row(
                   modifier = Modifier.padding(padding)
                ) {
                    Navigation(navController = navController)
                }

            }
        }
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.CharactersScreen.route
    ) {

        composable(route = Screens.CharactersScreen.route) {
            CharactersScreen()
        }
        composable(route = Screens.WeaponsScreen.route) {
            WeaponsScreen()
        }
        composable(route = Screens.HomeScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screens.ArtifactsScreen.route) {
            ArtifactsScreen()
        }
        composable(route = Screens.MapScreen.route) {
            MapScreen()
        }
    }
}

