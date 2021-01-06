package pl.merdala.core.usecase

import pl.merdala.core.repository.NoteRepository

class GetAllNotes(private val repository: NoteRepository) {
    suspend operator fun invoke() = repository.getAll()
}