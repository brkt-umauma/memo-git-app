package org.example.demo.editor.UI

import org.example.demo.editor.document.Document

object HistoryManager {


    fun pushState(doc: Document) {
        doc.undoStack.add(doc.text)
        doc.redoStack.clear()

        println("pushState: ${doc.text}")
        println("undoStack: $doc.undoStack")
        println("redoStack: ${doc.redoStack}")
    }

    // TODO: ショートカットキーとの競合解消
    fun undo(doc: Document): String? {
        // undo可否の確認
        if(doc.undoStack.isEmpty()){
            return null
        }

        println("undoStack: $doc.undoStack")
        println("redoStack: $doc.redoStack")

        doc.redoStack.add(doc.text)

        println("after undoStack: $doc.undoStack")
        println("after redoStack: $doc.redoStack")

        return doc.undoStack.removeLast()
    }

    fun redo(doc: Document): String? {
        //redo可否の確認
        if(doc.redoStack.isEmpty()){
            return null
        }

        println("undoStack: $doc.undoStack")
        println("redoStack: $doc.redoStack")

        doc.undoStack.add(doc.text)

        println("after undoStack: $doc.undoStack")
        println("after redoStack: ${doc.redoStack}")

        return doc.redoStack.removeLast()

    }

}