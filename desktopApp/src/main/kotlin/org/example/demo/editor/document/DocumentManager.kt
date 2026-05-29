package org.example.demo.editor.document

class DocumentManager {
    private val documents = mutableListOf<Document>()

    var activeIndex = -1
        private set

    val activeDocument: Document?
        get() = documents.getOrNull(activeIndex)

    val allDocuments: List<Document>
        get() = documents.toList()

    // 新規作成メソッド
    fun createDocument() {
        documents.add(Document())
        activeIndex = documents.lastIndex
    }

    // 
}