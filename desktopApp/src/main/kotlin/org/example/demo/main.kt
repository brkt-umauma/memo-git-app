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

        var doc by remember {
            mutableStateOf(Document("", null))
        }

        Column {

            Row {
                Button(onClick = {
                    val savedPath = saveAs(doc.text)

                    // パスを上書き：「ドキュメント」状態として記憶する
                    if (savedPath != null) {
                        doc = doc.copy(currentFilePath = savedPath)
                        println(doc.currentFilePath.toString())
                    }
                })
                { Text("保存") }


                Button(onClick = {
                    val savedPath = overwriteSave(doc.currentFilePath, doc.text)

                    // 上書き保存: currentFilePathにsavedPathを代入
                    if (savedPath != null) {
                        doc = doc.copy(currentFilePath = savedPath)
                    }
                })
                { Text("上書き保存") }


                Button(onClick = {
                    val result = openFile()

                    // 読み込み
                    // Pairの一つ目の戻り値をテキスト内容、
                    // 二つ目の戻り値をパスとして状態に持つ
                    if (result != null) {

                        doc = Document(
                            text = result.first,
                            currentFilePath = result.second
                        )

                        println("FILE OPENED")
                        println(result.second)
                    }
                })
                { Text("開く") }

                Button(onClick = {
                    val undoneText = HistoryManager.undo(doc)

                    // undoneTextがnullでない場合に履歴を更新
                    // つまりtextに代入
                    if (undoneText != null) {
                        doc = doc.copy(text = undoneText)
                    }
                })
                {Text("取り消し")}

                Button(onClick = {
                    val redoneText = HistoryManager.redo(doc)

                    // redoneTextがnullでない場合に履歴を更新
                    if (redoneText != null) {
                        doc = doc.copy(text = redoneText)
                    }

                })
                {Text("元に戻す")}
            }


            // エディタフィールド
            BasicTextField(
                value = doc.text,
                onValueChange = {
                    HistoryManager.pushState(doc)
                    doc = doc.copy(text = it)
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}