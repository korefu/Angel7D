package com.korefu.angel7d.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.korefu.angel7d.R
import com.korefu.angel7d.data.FormTypes
import com.korefu.angel7d.ui.about.AboutScreen
import com.korefu.angel7d.ui.feedback.FeedbackScreen
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
                    composable(route = "main/contacts") {
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
fun MainScreen(navController: NavController, viewModel: MainViewModel = viewModel()) {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        ModalDrawer(
            drawerState = drawerState,
            content = {
                Body(
                    onMenuClick = { scope.launch { drawerState.open() } },
                    onButtonClick = { name, message, contactInfo, town ->
                        viewModel.viewModelScope.launch {
                            viewModel.sendMessage(name, message, contactInfo, town)
                        }
                    })
            },
            drawerContent = {
                DrawerBody(onItemClick = {
                    navController.navigate("main/${it}")
                })
            })
    }
}

@Composable
fun Body(onMenuClick: () -> Unit, onButtonClick: (String, String, String, String) -> Unit) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Title(onMenuClick = onMenuClick)
        HelpOfferItem(
            painter = painterResource(R.drawable.psycho_help),
            formType = FormTypes.PSYCHOLOGICAL,
            onButtonClick = onButtonClick
        )
        HelpOfferItem(
            painter = painterResource(id = R.drawable.advice_help),
            formType = FormTypes.ADVICE,
            onButtonClick = onButtonClick
        )
        HelpOfferItem(
            painter = painterResource(id = R.drawable.prayer_help),
            formType = FormTypes.PRAYER,
            onButtonClick = onButtonClick
        )
    }
}


@Composable
fun HelpOfferItem(
    painter: Painter,
    formType: FormTypes,
    onButtonClick: (String, String, String, String) -> Unit
) {
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
                    onClick = {
                        onButtonClick(
                            nameTextField,
                            messageTextField,
                            contactInfoTextField,
                            townTextField
                        )
                    }
                )
            }
        }
    }

}