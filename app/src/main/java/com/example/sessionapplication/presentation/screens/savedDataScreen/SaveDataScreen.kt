package com.example.sessionapplication.presentation.screens.savedDataScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.sessionapplication.presentation.screens.home.composable.ListArticles
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveDataScreen(viewModel: SaveDataViewModel, navController: NavHostController) {
    val state by viewModel.state.collectAsState()
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .padding(it)
            .fillMaxSize() ){
            ListArticles(list = state.list){

            }
            Button(onClick = { viewModel.deleteAll() }, modifier = Modifier.align(Alignment.BottomCenter)) {
                Text(text = "DeleteAll")
            }
        }
    }
}