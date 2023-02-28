package com.murat.noteapp_cleanarchitecture.domain.usecase

import com.murat.noteapp_cleanarchitecture.domain.repository.NoteRepository

class GetAllNotesUseCase(private val noteRepository: NoteRepository) {
    fun getAllNotes() = noteRepository.getAllNotes()
}