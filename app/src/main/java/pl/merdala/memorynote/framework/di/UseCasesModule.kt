package pl.merdala.memorynote.framework.di

import dagger.Module
import dagger.Provides
import pl.merdala.core.repository.NoteRepository
import pl.merdala.core.usecase.AddNote
import pl.merdala.core.usecase.GetAllNotes
import pl.merdala.core.usecase.GetNote
import pl.merdala.core.usecase.RemoveNote
import pl.merdala.memorynote.framework.UseCases

@Module
class UseCasesModule {
    @Provides
    fun provideUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetNote(repository),
        GetAllNotes(repository),
        RemoveNote(repository)
    )
}