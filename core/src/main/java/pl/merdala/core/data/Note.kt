package pl.merdala.core.data

data class Note(
    var title: String,
    var content: String,
    var creationDate: Long,
    var updateDate: Long,
    var id: Long = 0,
    var wordCount: Int = 0
)