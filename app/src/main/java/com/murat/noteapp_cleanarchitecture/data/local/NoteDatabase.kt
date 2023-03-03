package com.murat.noteapp_cleanarchitecture.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.murat.noteapp_cleanarchitecture.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDao
}