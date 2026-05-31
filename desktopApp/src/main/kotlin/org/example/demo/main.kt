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
import org.example.demo.editor.document.DocumentManager
import org.example.demo.editor.layout.LineBreaker
import org.example.demo.editor.view.EditorView


fun main() = application {

    // documentManager呼び出し
    val documentManger = remember {
        DocumentManager().apply { createDocument() }
    }

    // ドキュメントの初期化
    var doc by remember {
        mutableStateOf(documentManger.activeDocument!!)
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
                    // 未保存時警告ダイアログを表示
                    if(!confirmDiscard(doc)){
                        return@Button
                    }

                    documentManger.createDocument()
                    doc = documentManger.activeDocument!!

                })
                {Text("新規")}

                Button(onClick = {
                    val savedPath = FileSave.saveAs(doc.text)

                    // パスを上書き：「ドキュメント」状態として記憶する
                    if (savedPath != null) {
                        documentManger.markSaved(savedPath)
                        doc = documentManger.activeDocument!!
                    }
                })
                { Text("名前を付けて保存") }


                Button(onClick = {
                    val savedPath = FileSave.overwriteSave(
                        doc.currentFilePath, doc.text
                    )

                    // 上書き保存: currentFilePathにsavedPathを代入
                    if (savedPath != null) {

                        documentManger.markSaved(savedPath)
                        doc = documentManger.activeDocument!!

//                        println(
//                            "text=${savedDoc.text}"
//                        )
//
//                        println(
//                            "saved=${savedDoc.savedText}"
//                        )
//
//                        println(
//                            "dirty=${savedDoc.isDirty}"
//                        )

                    }
                })
                { Text("上書き保存") }


                Button(onClick = {
                    // 未保存時警告ダイアログを表示
                    if(!confirmDiscard(doc)){
                        return@Button
                    }

                    val result = openFile()

                    // 読み込み
                    // Pairの一つ目の戻り値をテキスト内容、
                    // 二つ目の戻り値をパスとして状態に持つ
                    if (result != null) {

                        documentManger.openDocument(
                            result.first,
                            result.second
                        )

                        doc = documentManger.activeDocument!!

                        println("FILE OPENED")
                        println(result.second)
                    }
                })
                { Text("開く") }

                Button(onClick = {
                    HistoryManager.undo(documentManger)

                    doc = documentManger.activeDocument!!

                })
                {Text("取り消し")}

                Button(onClick = {
                     HistoryManager.redo(documentManger)

                     doc = documentManger.activeDocument!!

                })
                {Text("元に戻す")}
            }

            // 表示行定数の設定
            val visualLines = LineBreaker.buildVisualLines(doc.text)

            // エディタフィールド
            EditorView(
                doc = doc,
                onDocumentChange = { updated ->
                    documentManger.updateActiveDocument { updated }
                    doc = documentManger.activeDocument!!
                }
            )

        }
    }
}