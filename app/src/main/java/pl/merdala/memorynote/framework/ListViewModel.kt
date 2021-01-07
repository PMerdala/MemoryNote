package pl.merdala.memorynote.framework

import android.app.Application
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import pl.merdala.core.data.Note

class ListViewModel(application: Application) : AbstractViewModel(application) {

    val notes = MutableLiveData<List<Note>>()

    fun getNotes() {
        coroutineScope.launch {
            val newNotes = useCases.getAllNotes()
            notes.postValue(newNotes)
        }
    }
}