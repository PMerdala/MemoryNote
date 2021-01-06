package pl.merdala.core.usecase

import pl.merdala.core.data.Note
import pl.merdala.core.repository.NoteRepository

class GetNote(private val repository: NoteRepository) {
    suspend operator fun invoke(id: Long): Note? = repository.get(id)
}