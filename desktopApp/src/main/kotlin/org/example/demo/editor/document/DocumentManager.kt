package org.example.demo.editor.document

import javax.xml.crypto.dsig.Transform

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

    //ドキュメントを開く
    fun openDocument(text: String, path: String?) {
        documents.add(Document(text= text, currentFilePath = path,savedText= text))
        activeIndex = documents.lastIndex
    }

    // アクティブなドキュメントの書き換え
    fun updateActiveDocument(transform: (Document) -> Document) {
        val current = activeDocument ?: return

        documents[activeIndex] = transform(current)
    }

    // 保存状態の更新
    fun markSaved(path: String?) {
        updateActiveDocument { doc ->
            doc.copy(currentFilePath = path, savedText = doc.text)
        }
    }
}