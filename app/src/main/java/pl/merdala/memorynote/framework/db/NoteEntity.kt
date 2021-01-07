package pl.merdala.memorynote.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.merdala.core.data.Note

@Entity(tableName = "notes")
data class NoteEntity(
    val title: String,
    val content: String,
    @ColumnInfo(name = "creation_date")
    val creationDate: Long,
    @ColumnInfo(name = "update_date")
    val updateDate: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
) {
    companion object {
        fun fromNote(note: Note) =
            NoteEntity(note.title, note.content, note.creationDate, note.updateDate, note.id)
    }

    fun toNote() = Note(title, content, creationDate, updateDate, id)

}