package com.ibashkimi.telegram.ui.login

import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.TextField
import androidx.ui.input.TextFieldValue
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.unit.dp

@Composable
fun WaitForNumberScreen(onEnter: (String) -> Unit) {
    AuthorizationScreen(
        title = "Enter phone number",
        message = "Please enter your number in international format",
        onEnter = onEnter
    )
}

@Composable
fun WaitForCodeScreen(onEnter: (String) -> Unit) {
    AuthorizationScreen(
        title = "Enter code",
        onEnter = onEnter
    )
}

@Composable
fun WaitForPasswordScreen(onEnter: (String) -> Unit) {
    AuthorizationScreen(
        title = "Enter password",
        onEnter = onEnter
    )
}

@Composable
private fun AuthorizationScreen(title: String, message: String? = null, onEnter: (String) -> Unit) {
    val executed = state { false }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(title) })
        },
        bodyContent = {
            if (executed.value) {
                CircularProgressIndicator()
            } else {
                val phoneNumber = state { TextFieldValue() }
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    TextField(
                        value = phoneNumber.value,
                        onValueChange = { phoneNumber.value = it },
                        textStyle = MaterialTheme.typography.h5
                    )
                    Divider(
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (message == null) {
                        Spacer(modifier = Modifier.preferredHeight(16.dp))
                    } else {
                        Text(message, modifier = Modifier.padding(16.dp))
                    }
                    Button(modifier = Modifier.gravity(Alignment.End), text = {
                        Text("Enter")
                    }, onClick = {
                        onEnter(phoneNumber.value.text)
                        executed.value = true
                    })
                }
            }
        }
    )
}
