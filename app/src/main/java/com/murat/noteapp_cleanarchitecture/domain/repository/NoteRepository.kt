package com.murat.noteapp_cleanarchitecture.domain.repository

import com.murat.noteapp_cleanarchitecture.domain.model.Note
import com.murat.noteapp_cleanarchitecture.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun addNote(note: Note):Flow<Resource<Unit>>

    fun editNote(note: Note):Flow<Resource<Unit>>

    fun getAllNotes() : Flow<Resource<List<Note>>>

    fun removeNote(note: Note):Flow<Resource<Unit>>
}