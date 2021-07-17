package com.korefu.angel7d.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korefu.angel7d.R
import com.korefu.angel7d.ui.common.AppHeader

@Composable
fun Title(onMenuClick: () -> Unit) {
    AppHeader(backgroundHeight = 300.dp) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        ) {
            IconButton(
                onClick = onMenuClick,
            ) {
                Icon(Icons.Filled.Menu, "", tint = Color.Black)
            }
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.title_line_one),
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(4.dp),
                color = Color.White
            )
            Text(
                text = stringResource(R.string.title_line_two),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(4.dp),
                color = Color.White
            )
            Text(
                text = stringResource(R.string.title_line_three),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(4.dp),
                color = Color.White
            )
        }
    }
}