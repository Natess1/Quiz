package com.example.quiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

private object Routes {
    const val HOME = "home"
    const val STATS = "stats"
    const val GAME = "game"
}

@Composable
fun AppNavigation() {
    val context = LocalContext.current
    remember { QuizStats.init(context) }

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onOpenStats = { navController.navigate(Routes.STATS) },
                onStartGame = { navController.navigate(Routes.GAME) }
            )
        }

        composable(Routes.STATS) {
            StatsScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.GAME) {
            GameScreen(onBack = { navController.popBackStack() })
        }
    }
}