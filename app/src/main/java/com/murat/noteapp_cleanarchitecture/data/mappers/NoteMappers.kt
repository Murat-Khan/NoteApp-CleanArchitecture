package com.murat.noteapp_cleanarchitecture.data.mappers

import com.murat.noteapp_cleanarchitecture.data.model.NoteEntity
import com.murat.noteapp_cleanarchitecture.domain.model.Note

fun NoteEntity.toNote() = Note(id, title, description)

fun Note.toNoteEntity(): NoteEntity = NoteEntity(id, title, description)