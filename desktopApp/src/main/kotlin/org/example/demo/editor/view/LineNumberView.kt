package org.example.demo.editor.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun LineNumberView(
    lineCount: Int
) {
    Column {
        for (i in 1..lineCount) {
            Text(i.toString())
        }
    }
}