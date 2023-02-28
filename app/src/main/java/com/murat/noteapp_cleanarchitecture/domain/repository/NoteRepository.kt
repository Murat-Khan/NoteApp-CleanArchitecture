package com.murat.noteapp_cleanarchitecture.domain.repository

import com.murat.noteapp_cleanarchitecture.domain.model.Note

interface NoteRepository {
    fun addNote(note: Note)

    fun editNote(note: Note)

    fun getAllNotes() : List<Note>

    fun removeNote(note: Note)
}