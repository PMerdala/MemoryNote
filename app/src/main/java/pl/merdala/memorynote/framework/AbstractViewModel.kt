package pl.merdala.memorynote.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import pl.merdala.memorynote.framework.di.ApplicationModule
import pl.merdala.memorynote.framework.di.DaggerViewModelComponent
import javax.inject.Inject

abstract class AbstractViewModel(application: Application) : AndroidViewModel(application) {

    protected val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    protected lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder().applicationModule(ApplicationModule(application)).build()
            .inject(this)
    }

}