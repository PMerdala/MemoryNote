package pl.merdala.memorynote.framework.di

import android.app.Application
import dagger.Module
import dagger.Provides
import pl.merdala.core.repository.NoteRepository
import pl.merdala.memorynote.framework.RoomNoteDataSource

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(application: Application) =
        NoteRepository(RoomNoteDataSource(application))
}