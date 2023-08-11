package com.example.sessionapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sessionapplication.presentation.screens.savedDataScreen.SaveDataScreen
import com.example.sessionapplication.presentation.screens.savedDataScreen.SaveDataViewModel
import com.example.sessionapplication.presentation.screens.home.HomeScreen
import com.example.sessionapplication.presentation.screens.home.HomeViewModel
import com.example.sessionapplication.ui.theme.SessionApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SessionApplicationTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController, startDestination = "home") {
                        composable("Home") {

                            val homeViewModel: HomeViewModel by viewModels()

                            HomeScreen(viewModel = homeViewModel, navController)
                        }
                        composable("savedData") {
                            val saveDataViewModel: SaveDataViewModel by viewModels()
                            SaveDataScreen(viewModel = saveDataViewModel,navController)
                        }

                    }
                }
            }
        }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        SessionApplicationTheme {
            Greeting("Android")
        }
    }