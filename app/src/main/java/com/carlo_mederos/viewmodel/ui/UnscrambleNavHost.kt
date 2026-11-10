package com.carlo_mederos.viewmodel.ui

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

object Routes {
    const val START = "start"

    const val ARG_PLAYER = "playerName"
    const val ARG_SCORE = "score"
    const val ARG_WORDS = "wordCount"

    const val GAME = "game/{$ARG_PLAYER}"
    const val RESULT = "result/{$ARG_PLAYER}/{$ARG_SCORE}/{$ARG_WORDS}"

    fun game(playerName: String) = "game/${Uri.encode(playerName)}"
    fun result(playerName: String, score: Int, wordCount: Int) =
        "result/${Uri.encode(playerName)}/$score/$wordCount"
}

@Composable
fun UnscrambleNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    gameViewModel: GameViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.START,
        modifier = modifier
    ) {
        composable(Routes.START) {
            StartScreen(onStartClick = { name ->
                gameViewModel.resetGame()
                navController.navigate(Routes.game(name))
            })
        }

        composable(
            route = Routes.GAME,
            arguments = listOf(navArgument(Routes.ARG_PLAYER) { type = NavType.StringType })
        ) { backStackEntry ->
            val playerName = backStackEntry.arguments?.getString(Routes.ARG_PLAYER).orEmpty()
            GameScreen(
                playerName = playerName,
                gameViewModel = gameViewModel,
                onGameOver = { score, words ->
                    navController.navigate(Routes.result(playerName, score, words)) {
                        popUpTo(Routes.START)
                    }
                }
            )
        }

        composable(
            route = Routes.RESULT,
            arguments = listOf(
                navArgument(Routes.ARG_PLAYER) { type = NavType.StringType },
                navArgument(Routes.ARG_SCORE) { type = NavType.IntType },
                navArgument(Routes.ARG_WORDS) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val args = backStackEntry.arguments
            val playerName = args?.getString(Routes.ARG_PLAYER).orEmpty()
            ResultScreen(
                playerName = playerName,
                score = args?.getInt(Routes.ARG_SCORE) ?: 0,
                wordCount = args?.getInt(Routes.ARG_WORDS) ?: 0,
                onPlayAgain = {
                    gameViewModel.resetGame()
                    navController.navigate(Routes.game(playerName)) {
                        popUpTo(Routes.START)
                    }
                },
                onExitToStart = {
                    navController.popBackStack(Routes.START, inclusive = false)
                }
            )
        }
    }
}
