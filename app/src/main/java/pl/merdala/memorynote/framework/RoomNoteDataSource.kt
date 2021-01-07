package pl.merdala.memorynote.framework

import android.content.Context
import pl.merdala.core.data.Note
import pl.merdala.core.repository.NoteDataSource
import pl.merdala.memorynote.framework.db.DatabaseService
import pl.merdala.memorynote.framework.db.NoteEntity

class RoomNoteDataSource(context: Context) : NoteDataSource {
    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long): Note? = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll(): List<Note> =
        noteDao.getAllNoteEntities().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteEntity(NoteEntity.fromNote(note))
}