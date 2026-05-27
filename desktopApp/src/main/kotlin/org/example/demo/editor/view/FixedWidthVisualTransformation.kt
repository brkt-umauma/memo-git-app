package org.example.demo.editor.view

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import org.example.demo.editor.layout.LayoutEngine

class FixedWidthVisualTransformation(
    private val maxWidth: Int =80
): VisualTransformation {

    override fun filter(
        text: AnnotatedString
    ): TransformedText {

        // レイアウト
        val result = LayoutEngine.layout(text.text)

        // マッピング設定
        val offsetMapping = object : OffsetMapping {

            override fun originalToTransformed(offset: Int): Int {

                return result.sourceToDisplay.getOrElse(offset){
                    result.displayText.length
                }
            }

            override fun transformedToOriginal(offset: Int): Int {

                return result.displayToSource.getOrElse(offset){
                    text.length
                }
            }
        }

        return TransformedText(
            AnnotatedString(result.displayText),
            offsetMapping
        )

    }
}