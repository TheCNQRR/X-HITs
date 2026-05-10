package com.example.xhits

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
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
import com.example.auth.presentation.signin.SignInState
import com.example.auth.presentation.signin.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                        onIntent = signInViewModel::dispatchIntent,
                        viewModel = signInViewModel
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
                    val state by signInViewModel.state.collectAsState()

                    LaunchedEffect(code) {
                        code?.let { signInViewModel.dispatchIntent(SignInIntent.GoogleAuthCodeReceived(it)) }
                    }

                    LaunchedEffect(state) {
                        if (state is SignInState.Success) {
                            navController.navigate("home") {
                                popUpTo("sign_in") { inclusive = true }
                            }
                        }
                    }
                }

                composable("home") {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Welcome Home!", fontSize = 24.sp, color = Color.White)
                    }
                }
            }
        }
    }
}
