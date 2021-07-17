package com.korefu.angel7d.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korefu.angel7d.R
import com.korefu.angel7d.data.FormType

@Composable
fun DrawerBody(onItemClick: (String) -> Unit) {
    Column {
        DrawerHeader()
        Divider()
        DrawerItemMenu(text = stringResource(R.string.main_page), onItemClick = onItemClick, route = "")
        DrawerItemMenu(
            text = stringResource(FormType.FEEDBACK.titleId),
            onItemClick = onItemClick,
            route = "/" + FormType.FEEDBACK.name
        )
        DrawerItemMenu(
            text = stringResource(R.string.additional_info),
            onItemClick = onItemClick,
            route = "/contacts"
        )
        Divider()
        DrawerItemHeader(text = stringResource(R.string.useful_links))
        DrawerItemLink(text = "7D формат", url = "http://proekt7d.ru/")
        DrawerItemLink(text = "Сокрытое сокровище", url = "http://sokrsokr.net/")
    }
}

@Composable
private fun DrawerHeader() {
    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(stringResource(id = R.string.app_name), fontSize = 26.sp)
    }
}

@Composable
private fun DrawerItemHeader(text: String) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(16.dp),
            fontSize = 14.sp
        )
    }
}

@Composable
private fun DrawerItemMenu(text: String, onItemClick: (String) -> Unit, route: String) {
    Row(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(route) })
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clip(MaterialTheme.shapes.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text, fontSize = 16.sp)
    }
}

@Composable
private fun DrawerItemLink(text: String, url: String) {
    val uriHandler = LocalUriHandler.current
    Box(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .clickable(onClick = { uriHandler.openUri(url) })
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clip(MaterialTheme.shapes.medium),
    ) {
        Text(
            text,
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(end = 16.dp)
        )
        Icon(
            Icons.Filled.ExitToApp,
            "",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
        )
    }
}