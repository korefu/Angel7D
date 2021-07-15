package com.korefu.angel7d

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.korefu.angel7d.ui.theme.Angel7DTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @ExperimentalTextApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Angel7DTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main") {
                    composable(route = "main") {
                        MainScreen(navController)
                    }
                    composable(route = "contacts") {
                        AboutScreen()
                    }
                    composable(route = FormTypes.FEEDBACK.name) {
                        FeedbackScreen()
                    }
                }
            }

        }
    }
}

@Composable
fun FeedbackScreen() {

}

@ExperimentalTextApi
@Composable
fun AboutScreen() {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = "background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
                Box( // gradient
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color(0x88000000)
                                )
                            )
                        )
                )
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
                            append("По всем вопросам сотрудничества и другим интересующих вас вопросам можете связаться с нами:\n")
                            append("Электронная почта: ")
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
                                    append("Страница Вконтакте")
                                }
                            }
                            append("\n")
                            append("Вы можете звонить по Viber, Telegram, WhatsApp или горячей линии: ")
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
                    val uriHandler = LocalUriHandler.current
                    val context = LocalContext.current
                    ClickableText(
                        modifier = textModifier,
                        text = annotatedText,
                        onClick = { offset ->
                            annotatedText.getStringAnnotations(
                                "URL",
                                start = offset,
                                end = offset
                            ).firstOrNull()
                                ?.let { annotation -> uriHandler.openUri(annotation.item) }
                            annotatedText.getStringAnnotations(
                                "MAIL",
                                start = offset,
                                end = offset
                            ).firstOrNull()?.let { annotation ->
                                val intent = Intent(Intent.ACTION_VIEW)
                                val data: Uri =
                                    Uri.parse("mailto:${annotation.item}?subject=" + "" + "&body=" + "")
                                intent.data = data
                                context.startActivity(intent)
                            }
                            annotatedText.getStringAnnotations(
                                "PHONE",
                                start = offset,
                                end = offset
                            ).firstOrNull()?.let { annotation ->
                                val intent = Intent(Intent.ACTION_DIAL)
                                intent.data = Uri.parse("tel:${annotation.item}")
                                context.startActivity(intent)
                            }
                        })
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

                        Text("Яндекс деньги: 410012912454602", modifier = textModifier)
                        Text("Карта: 5536 9139 3849 8520", modifier = textModifier)
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        ModalDrawer(
            drawerState = drawerState,
            content = {
                Body(
                    onMenuClick = { scope.launch { drawerState.open() } })
            },
            drawerContent = {
                DrawerBody(onItemClick = {
                    navController.navigate(it)
                })
            })
    }
}

@Composable
fun Title(onMenuClick: () -> Unit) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Box( // gradient
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0x88000000)
                        )
                    )
                )
        )
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
                text = "7 дней в неделю мы готовы прийти на помощь.",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(4.dp),
                color = Color.White
            )
            Text(
                text = "Ангелы всегда рядом",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(4.dp),
                color = Color.White
            )
            Text(
                text = "Профессиональные психологи готовы вам помочь!",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(4.dp),
                color = Color.White
            )
        }
    }
}

@Composable
fun Body(onMenuClick: () -> Unit) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Title(onMenuClick = onMenuClick)
        HelpOfferItem(
            painter = painterResource(R.drawable.psycho_help),
            formType = FormTypes.PSYCHOLOGICAL
        )
        HelpOfferItem(
            painter = painterResource(id = R.drawable.advice_help),
            formType = FormTypes.ADVICE
        )
        HelpOfferItem(
            painter = painterResource(id = R.drawable.prayer_help),
            formType = FormTypes.PRAYER
        )
    }
}

@Composable
fun DrawerBody(onItemClick: (String) -> Unit) {
    Column {
        DrawerHeader()
        Divider()
        DrawerItemMenu(
            text = stringResource(FormTypes.FEEDBACK.titleId),
            onItemClick = onItemClick,
            route = FormTypes.FEEDBACK.name
        )
        DrawerItemMenu(
            text = stringResource(R.string.additional_info),
            onItemClick = onItemClick,
            route = "contacts"
        )
        Divider()
        DrawerItemHeader(text = "Полезные ссылки")
        DrawerItemLink(text = "7D формат", url = "http://proekt7d.ru/")
        DrawerItemLink(text = "Сокрытое сокровище", url = "http://sokrsokr.net/")
    }
}

@Composable
private fun DrawerHeader() {
    Row(modifier = Modifier.padding(16.dp), verticalAlignment = CenterVertically) {
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
        verticalAlignment = CenterVertically
    ) {
        Text(text, fontSize = 16.sp)
    }
}

@Composable
private fun DrawerItemLink(text: String, url: String) {
    val uriHandler = LocalUriHandler.current
    Row(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .clickable(onClick = { uriHandler.openUri(url) })
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clip(MaterialTheme.shapes.medium),
        verticalAlignment = CenterVertically
    ) {
        Text(text, fontSize = 16.sp)
        Icon(Icons.Filled.ExitToApp, "")
    }
}

@Composable
fun HelpOfferItem(painter: Painter, formType: FormTypes) {
    var isExpanded by remember { mutableStateOf(false) }
    var nameTextField by remember { mutableStateOf("") }
    var messageTextField by remember { mutableStateOf("") }
    var contactInfoTextField by remember { mutableStateOf("") }
    var townTextField by remember { mutableStateOf("") }
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing
                )
            )
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
            .clickable(onClick = { isExpanded = !isExpanded })
    ) {
        Column {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.height(120.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(formType.titleId),
                fontSize = 22.sp,
                modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 0.dp)
            )
            Text(
                text = if (isExpanded)
                    stringResource(formType.longDescriptionId)
                else
                    stringResource(formType.shortDescriptionId),
                fontSize = 14.sp,
                modifier = Modifier.padding(8.dp)
            )
            if (isExpanded) {
                val commonModifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp, 16.dp, 8.dp)
                TextField(
                    value = nameTextField, onValueChange = { nameTextField = it },
                    label = {
                        Text("Введите ваше имя")
                    }, modifier = commonModifier
                )
                TextField(
                    value = messageTextField, onValueChange = { messageTextField = it },
                    label = {
                        Text("Ваше сообщение")
                    }, modifier = commonModifier
                )
                TextField(
                    value = contactInfoTextField,
                    onValueChange = { contactInfoTextField = it },
                    label = {
                        Text("Введите куда Вам ответить")
                    }, modifier = commonModifier
                )
                TextField(
                    value = townTextField, onValueChange = { townTextField = it },
                    label = {
                        Text("С какого Вы города?")
                    }, modifier = commonModifier
                )
                Button(
                    shape = RoundedCornerShape(12.dp),
                    content = {
                        Text(
                            text = "Отправить сообщение",
                            modifier = Modifier.padding(8.dp)
                        )
                    },
                    modifier = commonModifier,
                    onClick = { /*TODO*/ })
            }
        }
    }

}