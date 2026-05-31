package org.example.demo.editor.UI

import org.example.demo.editor.document.Document
import org.example.demo.editor.document.DocumentManager

object HistoryManager {


    fun pushState(doc: Document) {
        doc.undoStack.add(doc.text)
        doc.redoStack.clear()

    }

    // TODO: ショートカットキーとの競合解消
    fun undo(manager: DocumentManager) {
        // undo可否の確認
        val doc = manager.activeDocument ?: return

        if(doc.undoStack.isEmpty()){
            return
        }

        doc.redoStack.add(doc.text)

        val previousText = doc.undoStack.removeLast()

        manager.updateActiveDocument {
            it.copy(text=previousText)
        }
    }

    fun redo(manager: DocumentManager) {
        //redo可否の確認
        val doc = manager.activeDocument ?: return

        if (doc.redoStack.isEmpty()){
            return
        }

        doc.undoStack.add(doc.text)

        val redoneText = doc.redoStack.removeLast()

        manager.updateActiveDocument {
            it.copy(text = redoneText)
        }

    }

}