package org.example.demo.editor.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.demo.editor.document.Document
import org.example.demo.editor.UI.HistoryManager
import org.example.demo.editor.layout.LineBreaker
import androidx.compose.foundation.layout.Row


@Composable
fun EditorView(
    doc: Document,
    onDocumentChange: (Document) -> Unit
) {

    val visualLines = LineBreaker.buildVisualLines(doc.text)

    Column {

        BasicTextField(
            value = doc.text,

            onValueChange = {
                HistoryManager.pushState(doc)

                val newDoc =
                    doc.copy(text = it)

                println(
                    "INPUT dirty=${newDoc.isDirty}"
                )

                onDocumentChange(newDoc)
            },

            textStyle = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontSize = 16.sp
            ),

           // visualTransformation = FixedWidthVisualTransformation(),

            modifier = Modifier.width(400.dp)
        )

        Row {

            LineNumberView(
                lineCount = visualLines.size
            )

            TextView(lines = visualLines)
        }
    }
}