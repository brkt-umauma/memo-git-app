package org.example.demo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Memo App",
    ) {

        var text by remember {
            mutableStateOf("")
        }

        BasicTextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxSize()
        )

    }
}