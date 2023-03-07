package com.murat.noteapp_cleanarchitecture.di

import android.content.Context
import androidx.room.Room
import com.murat.noteapp_cleanarchitecture.data.local.NoteDao
import com.murat.noteapp_cleanarchitecture.data.local.NoteDatabase
import com.murat.noteapp_cleanarchitecture.data.repository.NoteRepositoryImpl
import com.murat.noteapp_cleanarchitecture.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NoteModule {

    @Singleton
    @Provides
    fun provideNoteDatabase(
        @ApplicationContext context: Context
    ): NoteDatabase = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "note_db"
    ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase) = noteDatabase.noteDao()


    @Provides
    fun provideRepository(noteDao: NoteDao) : NoteRepository= NoteRepositoryImpl(noteDao)
}


