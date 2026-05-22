package org.example.demo

object HistoryManager {

    private val undoStack = mutableListOf<String>()
    private val redoStack = mutableListOf<String>()

    fun pushState(text: String) {
        undoStack.add(text)
        redoStack.clear()
    }

    fun undo(currentText: String): String? {
        // undo可否の確認
        if(undoStack.isEmpty()){
            return null
        }

        redoStack.add(currentText)

        return undoStack.removeLast()
    }

    fun redo(currentText: String): String? {
        //redo可否の確認
        if(redoStack.isEmpty()){
            return null
        }

        undoStack.add(currentText)

        return redoStack.removeLast()

    }
}