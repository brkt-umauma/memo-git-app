package org.example.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.demo.FIleLoad.openFile
import org.example.demo.FileSave.overwriteSave
import org.example.demo.FileSave.saveAs


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Memo App",
    ) {

        // text = 中身
        var text by remember {
            mutableStateOf("")
        }

        // currentFilePath = saveAs関数を実行したときに生成されるパス
        var currentFilePath by remember {
            mutableStateOf<String?>(null)
        }

        Column {
            Button(onClick = {
                val savedPath = saveAs(text)

                // パスを上書き：「ドキュメント」状態として記憶する
                if (savedPath != null) {
                    currentFilePath = savedPath
                    println(currentFilePath.toString())
                }
            })
            { Text("保存") }


            Button(onClick = {
                val savedPath = overwriteSave(currentFilePath, text)

                // 上書き保存: currentFilePathにsavedPathを代入
                if(savedPath != null) {
                    currentFilePath = savedPath
                }
            })
            { Text("上書き保存") }


            Button(onClick = {
                val result = openFile()

                // 読み込み
                // Pairの一つ目の戻り値をテキスト内容、
                // 二つ目の戻り値をパスとして状態に持つ
                if(result != null) {
                    text = result.first
                    currentFilePath = result.second
                }
            })
            { Text("開く")}


            // エディタフィールド
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