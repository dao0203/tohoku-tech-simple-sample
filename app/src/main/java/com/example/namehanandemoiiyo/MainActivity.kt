package com.example.namehanandemoiiyo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.namehanandemoiiyo.ui.theme.NamehaNandemoIiyoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NamehaNandemoIiyoTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "first",
                ) {
                    composable("first") {
                        FirstScreen(
                            onNavigateToSecondScreen = {
                                navController.navigate("second/$it")
                            }
                        )
                    }
                    composable(
                        "second/{number}",
                        arguments = listOf(
                            navArgument("number") { type = NavType.IntType }
                        )
                    ) {
                        val number = it.arguments?.getInt("number")
                        SecondScreen(number = number ?: 0)
                    }
                }
            }
        }
    }
}

@Composable
fun FirstScreen(
    onNavigateToSecondScreen: (Int) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                val number = (0..100).random()
                onNavigateToSecondScreen(number)
            }
        ) {
            Text(text = "2画面へ遷移")
        }
    }
}

@Composable
fun SecondScreen(
    number: Int
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Second Screen $number")
    }
}
