package com.murat.noteapp_cleanarchitecture.data.repository

import com.murat.noteapp_cleanarchitecture.data.local.NoteDao
import com.murat.noteapp_cleanarchitecture.data.mappers.toNote
import com.murat.noteapp_cleanarchitecture.data.mappers.toNoteEntity
import com.murat.noteapp_cleanarchitecture.domain.model.Note
import com.murat.noteapp_cleanarchitecture.domain.repository.NoteRepository

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {

    override fun addNote(note: Note) {
        noteDao.addNote(note.toNoteEntity())
    }

    override fun getAllNotes(): List<Note> {
        return noteDao.getAllNotes().map { it.toNote() }
    }

    override fun editNote(note: Note) {
        noteDao.editNote(note.toNoteEntity())
    }

    override fun removeNote(note: Note) {
        noteDao.removeNote(note.toNoteEntity())
    }
}