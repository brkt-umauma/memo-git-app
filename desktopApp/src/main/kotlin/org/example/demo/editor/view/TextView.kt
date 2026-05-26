package org.example.demo.editor.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.material.Text


@Composable
fun TextView(lines: List<String>) {

    Column {
        for (line in lines) {
            Text(line)
        }
    }
}