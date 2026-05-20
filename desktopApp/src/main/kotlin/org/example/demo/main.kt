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
import java.awt.FileDialog
import java.awt.Frame


fun saveAs(text: String){
    val fileDialog = FileDialog(
    null as Frame?,
    "名前を付けて保存",
    FileDialog.SAVE
    )

    fileDialog.isVisible = true

    val directory = fileDialog.directory
    val fileName = fileDialog.file

    if (directory != null && fileName != null) {

        val fullPath = directory + fileName

       FileSave.saveText(fullPath, text)

    }
}




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
                saveAs(text)
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