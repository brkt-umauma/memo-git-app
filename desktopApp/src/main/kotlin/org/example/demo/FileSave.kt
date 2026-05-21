package org.example.demo

import java.awt.FileDialog
import java.awt.Frame
import java.io.File

object FileSave {

    fun saveText(path: String, text: String) {

        val finalPath =
            if(path.endsWith(".txt")){
                path
            } else {
                "$path.txt"
            }


        File(finalPath).writeText(text, Charsets.UTF_8)
    }

    // 名前を付けて保存
    fun saveAs(text: String): String?{

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

            saveText(fullPath, text)

            return fullPath

        }

        return null
    }


    // 上書き保存
    fun overwriteSave(currentFilePath: String?, text: String): String? {

        if(currentFilePath != null){
            FileSave.saveText(currentFilePath, text)

            // 戻り値がパス = パスというstate＝ファイルの状態を返す
            return currentFilePath
        }

        return saveAs(text)
    }


}