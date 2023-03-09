package com.murat.noteapp_cleanarchitecture.domain.usecase

import com.murat.noteapp_cleanarchitecture.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
   private val noteRepository: NoteRepository
   ) {
   operator fun invoke() = noteRepository.getAllNotes()
}