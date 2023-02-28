package com.murat.noteapp_cleanarchitecture.domain.usecase

import com.murat.noteapp_cleanarchitecture.domain.model.Note
import com.murat.noteapp_cleanarchitecture.domain.repository.NoteRepository

class AddNoteUseCase(private val noteRepository: NoteRepository) {
    fun addNote(note: Note) = noteRepository.addNote(note)
}