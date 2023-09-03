package com.korefu.angel7d.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.korefu.angel7d.R
import com.korefu.angel7d.data.EmailDestination
import com.korefu.angel7d.data.FormData
import com.korefu.angel7d.data.FormType
import com.korefu.angel7d.data.Status
import kotlinx.coroutines.launch

@Composable
fun MessageForm(
    emailDestinations: List<EmailDestination>,
    formType: FormType,
    viewModel: MessageFormViewModel = remember { MessageFormViewModel() }
) {
    var nameTextField by remember { mutableStateOf("") }
    var messageTextField by remember { mutableStateOf("") }
    var contactInfoTextField by remember { mutableStateOf("") }
    var townTextField by remember { mutableStateOf("") }
    val loadingStatus = viewModel.loadingStatus.observeAsState(Status.WAITING)
    val commonModifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 8.dp, 16.dp, 8.dp)
    Box {
        Column {
            TextField(
                value = nameTextField, onValueChange = {
                    viewModel.loadingStatus.value = Status.WAITING
                    nameTextField = it
                },
                label = {
                    Text(stringResource(R.string.write_your_name))
                }, modifier = commonModifier,
                enabled = (loadingStatus.value != Status.LOADING)
            )
            TextField(
                value = messageTextField, onValueChange = {
                    viewModel.loadingStatus.value = Status.WAITING
                    messageTextField = it
                },
                label = {
                    Text(stringResource(R.string.your_message))
                }, modifier = commonModifier,
                enabled = (loadingStatus.value != Status.LOADING)
            )
            TextField(
                value = contactInfoTextField,
                onValueChange = {
                    viewModel.loadingStatus.value = Status.WAITING
                    contactInfoTextField = it
                },
                label = {
                    Text(stringResource(R.string.your_email_or_phone))
                }, modifier = commonModifier,
                enabled = (loadingStatus.value != Status.LOADING)
            )
            TextField(
                value = townTextField, onValueChange = {
                    viewModel.loadingStatus.value = Status.WAITING
                    townTextField = it
                },
                label = {
                    Text(stringResource(R.string.your_city))
                }, modifier = commonModifier,
                enabled = (loadingStatus.value != Status.LOADING)
            )
            Button(
                shape = RoundedCornerShape(12.dp),
                content = {
                    Text(
                        text = stringResource(R.string.send_message),
                        modifier = Modifier.padding(8.dp)
                    )
                },
                enabled = !(nameTextField == "" || messageTextField == ""
                        || townTextField == "" || contactInfoTextField == ""),
                modifier = commonModifier,
                onClick = {
                    viewModel.viewModelScope.launch {
                        viewModel.sendMessage(
                            FormData(
                                nameTextField,
                                messageTextField,
                                contactInfoTextField,
                                townTextField,
                                formType,
                                emailDestinations
                            )
                        )
                    }
                }
            )
            Text(
                text = when (loadingStatus.value) {
                    Status.FAILED -> stringResource(R.string.network_error)
                    Status.SUCCESS -> stringResource(R.string.message_sent)
                    else -> ""
                },
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        if (loadingStatus.value == Status.LOADING) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        if (loadingStatus.value == Status.SUCCESS) {
            nameTextField = ""
            messageTextField = ""
            townTextField = ""
            contactInfoTextField = ""
        }
    }
}
