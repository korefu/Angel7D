package com.korefu.angel7d.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BackArrow(navController: NavController, tint: Color = MaterialTheme.colors.onSurface) {
    IconButton(modifier = Modifier.padding(8.dp),
        onClick = {
            navController.navigate("main") {
                popUpTo("main") { inclusive = true }
            }
        }
    ) {
        Icon(
            Icons.Filled.ArrowBack,
            contentDescription = "back",
            tint = tint
        )
    }
}