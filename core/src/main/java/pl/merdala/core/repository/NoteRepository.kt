package pl.merdala.core.repository

import pl.merdala.core.data.Note

class NoteRepository(private val dataSource: NoteDataSource) {
    suspend fun add(note: Note) = dataSource.add(note)

    suspend fun get(id: Long): Note? = dataSource.get(id)

    suspend fun getAll() = dataSource.getAll()

    suspend fun remove(note: Note) = dataSource.remove(note)
}