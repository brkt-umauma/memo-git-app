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
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import java.io.File

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Memo App",
    ) {

        var text by remember {
            mutableStateOf("")
        }

        Column {
            Button(onClick = {
                File("memo.txt").writeText(text)
            })
            { Text("保存") }

            BasicTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                modifier = Modifier.fillMaxSize()
            )

        }
    }
}