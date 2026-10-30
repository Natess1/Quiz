package com.example.quiz

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

private object Routes {
    const val HOME = "home"
    const val STATS = "stats"
    const val GAME = "game"
}

private val QuizColors = darkColorScheme(
    primary = Color(0xFF9FB4C7),
    onPrimary = Color(0xFF1E1F22),
    background = Color(0xFF1E1F22),
    onBackground = Color(0xFFE6E6E6),
    surface = Color(0xFF26272B),
    onSurface = Color(0xFFE6E6E6),
    outline = Color(0xFF6B6F76)
)

@Composable
fun AppNavigation() {
    val context = LocalContext.current
    remember { QuizStats.init(context) }

    val navController = rememberNavController()

    MaterialTheme(
        colorScheme = QuizColors,
        typography = MaterialTheme.typography
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .safeDrawingPadding()
            ) {
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
        }
    }
}