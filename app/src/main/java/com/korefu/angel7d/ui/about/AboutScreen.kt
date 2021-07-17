package com.korefu.angel7d.ui.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.korefu.angel7d.R
import com.korefu.angel7d.ui.common.AppHeader
import com.korefu.angel7d.ui.common.BackArrow

@ExperimentalTextApi
@Composable
fun AboutScreen(
    navController: NavController
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        AppHeader(backgroundHeight = 250.dp) {
            BackArrow(navController = navController, tint = Color.Black)
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(
                        R.drawable.logo_transparent_light
                    ),
                    contentDescription = "logo",
                )
                Text(
                    color = Color.White,
                    fontSize = 26.sp, textAlign = TextAlign.Center, modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(), text = stringResource(
                        id = R.string.app_name
                    )
                )
            }
        }
        val textModifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 4.dp, 0.dp, 4.dp)
        Card(
            elevation = 4.dp,
            modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 0.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(stringResource(id = R.string.contacts_title), fontSize = 22.sp)
                val linkStyle = if (isSystemInDarkTheme()) SpanStyle(
                    color = Color.LightGray,
                    textDecoration = TextDecoration.Underline
                ) else SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )
                val annotatedText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.onSurface,
                            fontSize = MaterialTheme.typography.body1.fontSize
                        )
                    ) {
                        append(stringResource(R.string.contacts_desc))
                        append(stringResource(R.string.email_colon_space))
                        withStyle(
                            style = linkStyle
                        ) {
                            withAnnotation(
                                tag = "MAIL",
                                annotation = "angellove10@yandex.ru"
                            ) {
                                append("angellove10@yandex.ru")
                            }
                        }
                        append("\n")
                        withStyle(
                            style = linkStyle
                        ) {
                            withAnnotation(
                                tag = "URL",
                                annotation = "https://vk.com/turbo_ezhuk"
                            ) {
                                append(stringResource(R.string.vk_page))
                            }
                        }
                        append("\n")
                        append(stringResource(R.string.you_can_call))
                        withStyle(
                            style = linkStyle
                        ) {
                            withAnnotation(
                                tag = "PHONE",
                                annotation = "+79963360991"
                            ) {
                                append("+7(996)336-09-91")
                            }
                        }
                    }
                }
                TextWithLinks(annotatedText = annotatedText, modifier = textModifier)
            }
        }
        Card(
            elevation = 4.dp,
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            SelectionContainer {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(stringResource(id = R.string.donations_title), fontSize = 22.sp)
                    Text(stringResource(id = R.string.donations_text), modifier = textModifier)
                    Text(stringResource(R.string.requisites), modifier = textModifier)
                }
            }
        }
    }
}
