package pl.merdala.memorynote.framework

import android.app.Application
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import pl.merdala.core.data.Note

class NoteViewModel(application: Application) : AbstractViewModel(application) {

    val saved = MutableLiveData<Boolean>()

    fun saveNote(note: Note) {
        coroutineScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }
}