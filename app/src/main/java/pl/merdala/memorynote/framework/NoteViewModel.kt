package pl.merdala.memorynote.framework

import android.app.Application
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import pl.merdala.core.data.Note

class NoteViewModel(application: Application) : AbstractViewModel(application) {

    val saved = MutableLiveData<Boolean>()

    val currentNote = MutableLiveData<Note?>()

    fun saveNote(note: Note) {
        coroutineScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }

    fun getNote(id: Long) {
        coroutineScope.launch {
            val note = useCases.getNote(id)
            currentNote.postValue(note)
        }
    }
}