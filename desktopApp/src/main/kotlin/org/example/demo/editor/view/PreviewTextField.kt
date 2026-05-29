package org.example.demo.editor.view

import androidx.compose.runtime.Composable
import org.example.demo.editor.layout.LayoutEngine

@Composable
fun PreviewTextField(
    text: String
) {
    val result = LayoutEngine.layout(text)
}