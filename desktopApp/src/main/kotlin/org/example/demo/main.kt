package org.example.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.demo.editor.UI.DirtyDialog
import org.example.demo.editor.UI.DirtyDialog.confirmDiscard
import org.example.demo.editor.UI.FileLoad.openFile
import org.example.demo.editor.UI.FileSave
import org.example.demo.editor.UI.HistoryManager
import org.example.demo.editor.document.Document
import org.example.demo.editor.layout.LineBreaker
import org.example.demo.editor.view.EditorView


fun main() = application {
    var doc by remember {
        mutableStateOf(Document("", null, ""))
    }

    Window(

        onCloseRequest = {
            if(confirmDiscard(doc)) {
                exitApplication()
            }
        },
        title = "Scristoria",
    ) {


        Column {

            Row {
                Button(onClick = {
                    val savedPath = FileSave.saveAs(doc.text)

                    // パスを上書き：「ドキュメント」状態として記憶する
                    if (savedPath != null) {
                        doc = doc.copy(
                            currentFilePath = savedPath,
                            savedText = doc.text)
                    }
                })
                { Text("名前を付けて保存") }


                Button(onClick = {
                    val savedPath = FileSave.overwriteSave(
                        doc.currentFilePath, doc.text
                    )

                    // 上書き保存: currentFilePathにsavedPathを代入
                    if (savedPath != null) {

                        val savedDoc = doc.copy(
                            currentFilePath = savedPath,
                            savedText = doc.text
                        )

                        println(
                            "text=${savedDoc.text}"
                        )

                        println(
                            "saved=${savedDoc.savedText}"
                        )

                        println(
                            "dirty=${savedDoc.isDirty}"
                        )

                        doc = savedDoc
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
                            currentFilePath = result.second,
                            savedText = result.first
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

            // 表示行定数の設定
            val visualLines = LineBreaker.buildVisualLines(doc.text)

            // エディタフィールド
            EditorView(
                doc = doc,
                onDocumentChange = {doc = it}
            )

        }
    }
}