package org.example.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import org.example.demo.FileLoad.openFile
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

            Row {
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
                    if (savedPath != null) {
                        currentFilePath = savedPath
                    }
                })
                { Text("上書き保存") }


                Button(onClick = {
                    val result = openFile()

                    // 読み込み
                    // -> 履歴クリア
                    // Pairの一つ目の戻り値をテキスト内容、
                    // 二つ目の戻り値をパスとして状態に持つ
                    if (result != null) {

                        HistoryManager.clearHistory()

                        text = result.first
                        currentFilePath = result.second

                        println("FILE OPENED")
                        println(result.second)
                    }
                })
                { Text("開く") }

                Button(onClick = {
                    val undoneText = HistoryManager.undo(text)

                    // undoneTextがnullでない場合に履歴を更新
                    // つまりtextに代入
                    if (undoneText != null) {
                        text = undoneText
                    }
                })
                {Text("取り消し")}

                Button(onClick = {
                    val redoneText = HistoryManager.redo(text)

                    // redoneTextがnullでない場合に履歴を更新
                    if (redoneText != null) {
                        text = redoneText
                    }

                })
                {Text("元に戻す")}
            }


            // エディタフィールド
            BasicTextField(
                value = text,
                onValueChange = {
                    HistoryManager.pushState(text)
                    text = it
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}