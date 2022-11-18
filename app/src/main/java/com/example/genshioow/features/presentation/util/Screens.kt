package com.example.genshioow.features.presentation.util

sealed class Screens(val route: String) {
    object HomeScreen : Screens("home_screen")
    object CharactersScreen : Screens("char_screen")
    object WeaponsScreen: Screens("weapons_screen")
    object ArtifactsScreen: Screens("art_screen")
    object MapScreen: Screens("map_screen")
}
