package com.example.sessionapplication.presentation.screens.home.composable

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun ErrorDialog(shouldShow : Boolean = false,message: String, onDismiss: () -> Unit, onConfirmClick: () -> Unit) {
    if (!shouldShow) return
    AlertDialog(
        title = { Text(text = "Error", fontSize = 20.sp) },
        text = { Text(text = message) },
        onDismissRequest =  onDismiss::invoke,
        confirmButton = {
            Button(onClick =onConfirmClick::invoke) {
                Text(text = "Retry")
            }
        })
}