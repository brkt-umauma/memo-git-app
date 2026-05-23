package org.example.demo

object HistoryManager {

    private val undoStack = mutableListOf<String>()
    private val redoStack = mutableListOf<String>()

    fun pushState(text: String) {
        undoStack.add(text)
        redoStack.clear()

        println("pushState: $text")
        println("undoStack: $undoStack")
        println("redoStack: $redoStack")
    }

    fun undo(currentText: String): String? {
        // undo可否の確認
        if(undoStack.isEmpty()){
            return null
        }

        println("undoStack: $undoStack")
        println("redoStack: $redoStack")

        redoStack.add(currentText)

        println("after undoStack: $undoStack")
        println("after redoStack: $redoStack")

        return undoStack.removeLast()
    }

    fun redo(currentText: String): String? {
        //redo可否の確認
        if(redoStack.isEmpty()){
            return null
        }

        println("undoStack: $undoStack")
        println("redoStack: $redoStack")

        undoStack.add(currentText)

        println("after undoStack: $undoStack")
        println("after redoStack: $redoStack")

        return redoStack.removeLast()

    }

    fun clearHistory() {
        // undoStackを空にする（0523時点では履歴管理はApp）
        undoStack.clear()
        // redoStackを空にする（上に同じ）
        redoStack.clear()

        println("history cleared")
        println("before undo: $undoStack")
        println("before redo: $redoStack")
    }
}