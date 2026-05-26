package org.example.demo.editor.layout

object LineBreaker {

    fun buildVisualLines(
        text: String,
        maxWidth: Int = 80
    ): List<String> {

        val visualLines = mutableListOf<String>()

        val logicalLines = text.lines()

        for (line in logicalLines) {

            var currentWidth = 0
            var currentLine = ""

            for (c in line) {

                val width = charWidth(c)

                if (currentWidth + width > maxWidth) {

                    visualLines.add(currentLine)

                    currentLine = ""
                    currentWidth = 0
                }

                currentLine += c
                currentWidth += width
            }

            visualLines.add(currentLine)
        }

        return visualLines
    }

    private fun charWidth(c: Char): Int {

        return if (c.code <= 0x7F) {
            1
        } else {
            2
        }
    }
}