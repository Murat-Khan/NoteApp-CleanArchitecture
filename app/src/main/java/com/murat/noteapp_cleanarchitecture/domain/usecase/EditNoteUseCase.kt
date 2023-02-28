package com.murat.noteapp_cleanarchitecture.domain.usecase

import com.murat.noteapp_cleanarchitecture.domain.model.Note
import com.murat.noteapp_cleanarchitecture.domain.repository.NoteRepository

class EditNoteUseCase(private val noteRepository: NoteRepository) {
    fun editNote(note: Note) = noteRepository.editNote(note)
}