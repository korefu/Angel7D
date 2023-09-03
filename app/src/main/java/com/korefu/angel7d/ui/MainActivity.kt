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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.korefu.angel7d.R
import com.korefu.angel7d.data.EmailDestination
import com.korefu.angel7d.data.FormType
import com.korefu.angel7d.ui.about.AboutScreen
import com.korefu.angel7d.ui.common.MessageForm
import com.korefu.angel7d.ui.feedback.FeedbackScreen
import com.korefu.angel7d.ui.theme.Angel7DTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @ExperimentalTextApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Angel7DTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val drawerState = rememberDrawerState(DrawerValue.Closed)
                    val coroutineScope = rememberCoroutineScope()
                    val navController = rememberNavController()
                    ModalDrawer(
                        drawerState = drawerState,
                        content = {
                            NavHost(navController = navController, startDestination = "main") {
                                composable(route = "main") {
                                    MainScreen(
                                        coroutineScope = coroutineScope,
                                        drawerState = drawerState
                                    )
                                }
                                composable(route = "main/contacts") {
                                    AboutScreen(navController)
                                }
                                composable(route = "main/${FormType.FEEDBACK.name}") {
                                    FeedbackScreen(navController)
                                }
                            }
                        },
                        drawerContent = {
                            DrawerBody(onItemClick = {
                                navController.navigate("main${it}") {
                                    popUpTo("main")
                                    launchSingleTop = true
                                }
                                coroutineScope.launch { drawerState.close() }
                            })
                        })
                }
            }

        }
    }
}

@Composable
fun MainScreen(
    coroutineScope: CoroutineScope,
    drawerState: DrawerState
) {
    Body(
        onMenuClick = { coroutineScope.launch { drawerState.open() } })
}

@Composable
fun Body(onMenuClick: () -> Unit) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Title(onMenuClick = onMenuClick)
        HelpOfferItem(
            painter = painterResource(R.drawable.psycho_help),
            formType = FormType.PSYCHOLOGICAL,
            emailDestinations = listOf(
                EmailDestination.EMAIL_COMMON,
                EmailDestination.EMAIL_PSYCHOLOGICAL
            )
        )
        HelpOfferItem(
            painter = painterResource(id = R.drawable.advice_help),
            formType = FormType.ADVICE,
            emailDestinations = listOf(
                EmailDestination.EMAIL_COMMON,
                EmailDestination.EMAIL_ADVICE
            )
        )
    }
}


@Composable
fun HelpOfferItem(
    painter: Painter,
    formType: FormType,
    emailDestinations: List<EmailDestination>
) {
    var isExpanded by remember { mutableStateOf(false) }
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
                MessageForm(
                    emailDestinations = emailDestinations,
                    formType = formType
                )
            }
        }
    }

}
