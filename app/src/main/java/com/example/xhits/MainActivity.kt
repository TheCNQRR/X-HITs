package com.example.xhits

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.auth.presentation.signin.SignInIntent
import com.example.auth.presentation.signin.SignInScreen
import com.example.auth.presentation.signin.SignInViewModel

class MainActivity : AppCompatActivity() {

    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.apply {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        supportActionBar?.hide()

        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "sign_in"
            ) {
                composable("sign_in") {
                    val state by signInViewModel.state.collectAsState()

                    SignInScreen(
                        state = state,
                        onIntent = signInViewModel::dispatchIntent
                    )
                }

                composable(
                    "auth_callback?status={status}&code={code}",
                    arguments = listOf(
                        navArgument("status") {
                            type = NavType.StringType
                            nullable = true
                        },
                        navArgument("code") {
                            type = NavType.StringType
                            nullable = true
                        }
                    ),
                    deepLinks = listOf(
                        navDeepLink { uriPattern = "com.example.app://auth/{status}?code={code}" }
                    )
                ) { backStackEntry ->
                    val code = backStackEntry.arguments?.getString("code")

                    LaunchedEffect(code) {
                        code?.let { signInViewModel.dispatchIntent(SignInIntent.GoogleAuthCodeReceived(it)) }
                    }
                }
            }
        }
    }
}
