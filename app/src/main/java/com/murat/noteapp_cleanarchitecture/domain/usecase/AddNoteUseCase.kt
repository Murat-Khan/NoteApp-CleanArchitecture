package com.murat.noteapp_cleanarchitecture.domain.usecase

import com.murat.noteapp_cleanarchitecture.domain.model.Note
import com.murat.noteapp_cleanarchitecture.domain.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
    ) {
   // fun addNote(note: Note) = noteRepository.addNote(note)
    operator fun invoke(note: Note) = noteRepository.addNote(note)
}