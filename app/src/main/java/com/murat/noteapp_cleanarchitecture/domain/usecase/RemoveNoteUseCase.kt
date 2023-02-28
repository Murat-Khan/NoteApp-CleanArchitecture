package com.murat.noteapp_cleanarchitecture.domain.usecase

import com.murat.noteapp_cleanarchitecture.domain.model.Note
import com.murat.noteapp_cleanarchitecture.domain.repository.NoteRepository

class RemoveNoteUseCase(private val noteRepository: NoteRepository) {
    fun removeNote(note: Note) = noteRepository.removeNote(note)
}