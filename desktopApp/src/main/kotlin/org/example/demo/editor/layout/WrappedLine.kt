package org.example.demo.editor.layout

data class WrappedLine (

    // 表示用行番号
    val lineNumber: Int,

    // 表示文字列
    val text: String,

    // 元テキスト開始位置
    val sourceStartIndex: Int,

    // 元テキスト終了位置
    val sourceEndIndex: Int,

    // 表示幅
    val width: Int,

    // 行終了理由
    val breakType: LineBreakType
)