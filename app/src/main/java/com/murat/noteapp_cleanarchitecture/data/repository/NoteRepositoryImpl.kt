package com.murat.noteapp_cleanarchitecture.data.repository

import com.murat.noteapp_cleanarchitecture.data.base.BaseRepository
import com.murat.noteapp_cleanarchitecture.data.local.NoteDao
import com.murat.noteapp_cleanarchitecture.data.mappers.toNote
import com.murat.noteapp_cleanarchitecture.data.mappers.toNoteEntity
import com.murat.noteapp_cleanarchitecture.domain.model.Note
import com.murat.noteapp_cleanarchitecture.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository,BaseRepository() {

    override fun addNote(note: Note) = doRequest {
        noteDao.addNote(note.toNoteEntity())
    }


    override fun getAllNotes()= doRequest{
        noteDao.getAllNotes().map { it.toNote() }
    }


    override fun editNote(note: Note) = doRequest {
        noteDao.editNote(note.toNoteEntity())
    }


     override fun removeNote(note: Note) = doRequest{
        noteDao.removeNote(note.toNoteEntity())
    }
}