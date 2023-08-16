package com.example.sessionapplication.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.retrofitapp.presentation.screens.home.composable.LoadingScreen
import com.example.sessionapplication.presentation.screens.home.composable.ErrorDialog
import com.example.sessionapplication.presentation.screens.home.composable.ListArticles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = Unit){
        viewModel.getArticles()
    }
    Scaffold(modifier = Modifier.fillMaxSize()) {
        ErrorDialog(
            shouldShow = state.dialogModel?.isShouldShow ?: false,
            message = state.dialogModel?.message ?: "",
            onDismiss = { viewModel.dismissDialog() },
            onConfirmClick = {
                viewModel.dismissDialog()
                viewModel.getArticles()
            })
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LoadingScreen(isLoading = state.isLoading, modifier = Modifier.align(Alignment.Center))
            ListArticles(list = state.list, "Insert", isDependOnArticleFlag = true) { article, index ->
                if (!article.isInsert) {
                    viewModel.insertArticle(article, index)
                }
            }

            Button(
                onClick = { navController.navigate("savedData") }, modifier = Modifier.align(
                    Alignment.BottomCenter
                )
            ) {
                Text(text = "Navigate")
            }
        }
    }
}