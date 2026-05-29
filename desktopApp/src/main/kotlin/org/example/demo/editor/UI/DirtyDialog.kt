package org.example.demo.editor.UI

import org.example.demo.editor.document.Document
import javax.swing.JOptionPane

object DirtyDialog {

    fun confirmDiscard(doc: Document): Boolean {
        if (!doc.isDirty){
            return true
        }

        val result = JOptionPane.showConfirmDialog(
            null,
            "未保存の変更があります。破棄しますか？",
            "確認",
            JOptionPane.YES_NO_OPTION
        )

        return result == JOptionPane.YES_OPTION
    }
}