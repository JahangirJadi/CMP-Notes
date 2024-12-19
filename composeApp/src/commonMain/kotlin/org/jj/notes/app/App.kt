package org.jj.notes.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jj.notes.presentation.NotesScreenRoot

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Route.NotesGraph
        ){
            navigation<Route.NotesGraph>(
                startDestination = Route.NoteList,
            ){
                composable<Route.NoteList> {
                    NotesScreenRoot()
                }
            }
        }
    }
}