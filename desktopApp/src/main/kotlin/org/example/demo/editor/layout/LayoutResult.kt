package org.example.demo.editor.layout

data class LayoutResult (

    // 表示行
    val lines: List<WrappedLine>,

    // 表示用テキスト
    val displayText: String,

    // source -> display
    val sourceToDisplay: IntArray,

    // display -> source
    val displayToSource: IntArray
)