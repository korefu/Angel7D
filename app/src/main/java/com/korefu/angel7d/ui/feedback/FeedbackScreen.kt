package com.korefu.angel7d.ui.feedback

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.korefu.angel7d.R
import com.korefu.angel7d.data.EmailDestination
import com.korefu.angel7d.data.FormType
import com.korefu.angel7d.ui.common.BackArrow
import com.korefu.angel7d.ui.common.MessageForm

@Composable
fun FeedbackScreen(
    navController: NavController
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Image(
            painter = painterResource(
                if (isSystemInDarkTheme())
                    R.drawable.logo_transparent_light
                else R.drawable.logo_transparent_dark
            ),
            contentDescription = "logo",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
        Text(
            stringResource(R.string.rate_us),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Text(
            stringResource(R.string.feedback_desc),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        MessageForm(
            emailDestinations = listOf(EmailDestination.EMAIL_FEEDBACK),
            formType = FormType.FEEDBACK
        )
    }
    BackArrow(navController = navController)
}