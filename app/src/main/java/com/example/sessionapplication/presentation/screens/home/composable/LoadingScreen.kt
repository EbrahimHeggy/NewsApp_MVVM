package com.example.retrofitapp.presentation.screens.home.composable

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoadingScreen(isLoading : Boolean, modifier :Modifier = Modifier) {
    if(!isLoading) return
    CircularProgressIndicator(modifier = modifier)
}