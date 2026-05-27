package org.example.demo.editor.layout

object LayoutEngine {

    fun layout(
        text: String,
        config: LayoutConfig = LayoutConfig()
    ): LayoutResult {

        // 行のリスト
        val wrappedLines = mutableListOf<WrappedLine>()

        // 行のインデックスと行番号
        var globalIndex = 0
        var lineNumber = 1

        // 表示行
        val logicalLines = text.lines()

        // forループで行数カウント
        for (logicalLine in logicalLines) {

            // 初期値設定
            var currentWidth = 0
            var currentText = ""

            var currentStartIndex = globalIndex

            // 文字幅カウント
            for (c in logicalLine) {

                val charWidth = CharClassifier.widthOf(c)

                // 折り返し処理
                if (
                    currentWidth + charWidth > config.maxLineWidth
                ) {

                    // 表示幅超過による折り返し
                    wrappedLines.add(
                        WrappedLine(
                            lineNumber = lineNumber,
                            text = currentText,
                            sourceStartIndex = currentStartIndex,
                            sourceEndIndex = globalIndex - 1,
                            width = currentWidth,
                            breakType = LineBreakType.SOFT_WRAP
                        )
                    )

                    lineNumber++
                    currentText = ""
                    currentWidth = 0
                    currentStartIndex = globalIndex
                }

                currentText += c
                currentWidth += charWidth
                globalIndex++
            }

            // 改行タイプ判定
            val breakType = if (
                // 手動改行
                globalIndex < text.length
            ) {
                LineBreakType.MANUAL_BREAK
            } else {
                // 文末
                LineBreakType.END_OF_TEXT
            }

            wrappedLines.add(
                WrappedLine(
                    lineNumber = lineNumber,
                    text = currentText,
                    sourceStartIndex = currentStartIndex,
                    sourceEndIndex = globalIndex,
                    width = currentWidth,
                    breakType = breakType
                )
            )

            lineNumber++
            globalIndex++
        }

        // デバッグ用ログ
        for (line in wrappedLines) {

            println(
                "[${line.lineNumber}] " +
                        "'${line.text}' " +
                        "start=${line.sourceStartIndex} " +
                        "end=${line.sourceEndIndex} " +
                        "width=${line.width} " +
                        "break=${line.breakType}"
            )
        }

        val displayText = wrappedLines.joinToString("\n") {
            it.text
        }

        return LayoutResult(
            lines = wrappedLines,
            displayText = displayText,
            sourceToDisplay = IntArray(text.length + 1),
            displayToSource = IntArray(text.length + 1)
        )

    }

}
