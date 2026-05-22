package org.example.demo

import java.awt.FileDialog
import java.awt.Frame
import java.io.File
import javax.swing.JOptionPane
import javax.swing.JOptionPane.YES_NO_OPTION

object FileSave {

    fun saveText(path: String, text: String) {

        val finalPath =
            if (path.endsWith(".txt")) {
                path
            } else {
                "$path.txt"
            }


        File(finalPath).writeText(text, Charsets.UTF_8)
    }

    // 名前を付けて保存
    fun saveAs(text: String): String? {

        val fileDialog = FileDialog(
            null as Frame?,
            "名前を付けて保存",
            FileDialog.SAVE
        )

        fileDialog.isVisible = true

        val directory = fileDialog.directory
        val fileName = fileDialog.file

        if (directory != null && fileName != null) {

            val fullPath =
                File(directory, fileName).path

            val file = File(fullPath)

            if(file.exists()) {
                val result = JOptionPane.showConfirmDialog(
                    null,
                    "既に同名のファイルが存在します。上書きしますか？",
                    "確認",
                    YES_NO_OPTION
                )

                if(result != JOptionPane.YES_OPTION){
                    return null
                }
            }

            saveText(fullPath, text)

            return fullPath

        }

        return null
    }


    // 上書き保存
    fun overwriteSave(currentFilePath: String?, text: String): String? {

        if (currentFilePath != null) {
            FileSave.saveText(currentFilePath, text)

            // 戻り値がパス = パスというstate＝ファイルの状態を返す
            return currentFilePath
        }

        return saveAs(text)
    }


}