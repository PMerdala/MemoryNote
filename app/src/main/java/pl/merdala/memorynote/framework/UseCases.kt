package pl.merdala.memorynote.framework

import pl.merdala.core.usecase.AddNote
import pl.merdala.core.usecase.GetAllNotes
import pl.merdala.core.usecase.GetNote
import pl.merdala.core.usecase.RemoveNote

data class UseCases(
    val addNote: AddNote,
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val removeNote: RemoveNote
)