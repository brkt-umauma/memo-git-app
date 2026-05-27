package org.example.demo.editor.layout

object CharClassifier {

    // 文字幅を返す関数
    fun widthOf(c: Char): Int{

        return if(isHalfWidth(c)) {
            1
        } else {
            2
        }
    }

    // 全角／半角判定
    fun isHalfWidth(c: Char): Boolean{

        val code = c.code
        return when{
            // ASCII
            code in 0x00..0x7F -> true

            // 半角カタカナ
            code in 0xFF61..0xFF9F -> true

            else -> false
        }
    }
}