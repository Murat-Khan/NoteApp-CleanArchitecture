package com.murat.noteapp_cleanarchitecture.data.local

import androidx.room.*
import com.murat.noteapp_cleanarchitecture.data.model.NoteEntity

@Dao
interface NoteDao {
    @Insert
    fun addNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes ")
    fun getAllNotes() : List<NoteEntity>

    @Update
    fun editNote(noteEntity: NoteEntity)

    @Delete
    fun removeNote(noteEntity: NoteEntity)
}