package org.example.demo

import java.io.File

object FileSave {

    fun saveText(path: String, text: String) {

        val finalPath =
            if(path.endsWith(".txt")){
                path
            } else {
                "$path.txt"
            }


        File(finalPath).writeText(text)
    }

}