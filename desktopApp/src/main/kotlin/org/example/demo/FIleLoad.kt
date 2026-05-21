package org.example.demo

import java.awt.FileDialog
import java.awt.Frame
import java.io.File

object FIleLoad {

    // ファイルの呼び出し＋読み込み
    fun openFile(): Pair<String, String>? {

        val fileDialog = FileDialog(
            null as Frame?,
            "開く",
            FileDialog.LOAD
        )

        fileDialog.isVisible = true

        val directory = fileDialog.directory
        val fileName = fileDialog.file

        val fullPath =
            File(directory, fileName).path

        val text =
            File(fullPath).readText(Charsets.UTF_8)

        return Pair(text, fullPath)
    }
}