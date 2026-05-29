package org.example.demo.editor.document

data class Document(
    // テキストの状態：text（中身）、currentFilePath（パス）
    val text: String = "",
    val currentFilePath: String? = null,

    // 保存済テキスト
    val savedText: String = "",

    // テキストの履歴管理
    val undoStack: MutableList<String> = mutableListOf(),
    val redoStack: MutableList<String> = mutableListOf()
){
    // 未保存／保存済
    val isDirty: Boolean
        get() = text != savedText
}