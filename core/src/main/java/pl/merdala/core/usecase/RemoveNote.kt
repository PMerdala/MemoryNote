package pl.merdala.core.usecase

import pl.merdala.core.data.Note
import pl.merdala.core.repository.NoteRepository

class RemoveNote(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) = repository.remove(note)
}