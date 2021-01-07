package pl.merdala.memorynote.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import pl.merdala.core.repository.NoteRepository
import pl.merdala.core.usecase.AddNote
import pl.merdala.core.usecase.GetAllNotes
import pl.merdala.core.usecase.GetNote
import pl.merdala.core.usecase.RemoveNote

abstract class AbstractViewModel(application: Application) : AndroidViewModel(application) {

    protected val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repository = NoteRepository(RoomNoteDataSource(application))

    protected val useCases = UseCases(
        AddNote(repository),
        GetNote(repository),
        GetAllNotes(repository),
        RemoveNote(repository)
    )

}