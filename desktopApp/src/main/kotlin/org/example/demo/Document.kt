package org.example.demo

data class Document(
    // テキストの状態：text（中身）、currentFilePath（パス）
    val text: String,
    val currentFilePath: String?,

    // テキストの履歴管理
    val undoStack: MutableList<String> = mutableListOf(),
    val redoStack: MutableList<String> = mutableListOf()
)