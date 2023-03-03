package com.murat.noteapp_cleanarchitecture.data.repository

import com.murat.noteapp_cleanarchitecture.data.local.NoteDao
import com.murat.noteapp_cleanarchitecture.data.mappers.toNote
import com.murat.noteapp_cleanarchitecture.data.mappers.toNoteEntity
import com.murat.noteapp_cleanarchitecture.domain.model.Note
import com.murat.noteapp_cleanarchitecture.domain.repository.NoteRepository
import com.murat.noteapp_cleanarchitecture.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {

    override fun addNote(note: Note):Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val data = noteDao.addNote(note.toNoteEntity())
            emit(Resource.Success(data))

        }catch (ioException: Exception){

            emit(Resource.Error(ioException.localizedMessage?:"Не известная ошибка"))
        }
       // emit(noteDao.addNote(note.toNoteEntity()))
    }.flowOn(Dispatchers.IO)

    override fun getAllNotes(): Flow<Resource<List<Note>>> = flow{

        emit(Resource.Loading())
        try {
            val data = noteDao.getAllNotes().map { it.toNote() }
            emit(Resource.Success(data))

        }catch (ioException: Exception){

            emit(Resource.Error(ioException.localizedMessage?:"Не известная ошибка"))
        }


            // emit(noteDao.getAllNotes().map { it.toNote() })
    }.flowOn(Dispatchers.IO)

    override fun editNote(note: Note): Flow<Resource<Unit>> = flow {

        emit(Resource.Loading())
        try {
            val data = noteDao.editNote(note.toNoteEntity())
            emit(Resource.Success(data))

        }catch (ioException: Exception){

            emit(Resource.Error(ioException.localizedMessage?:"Не известная ошибка"))
        }
       // emit(noteDao.editNote(note.toNoteEntity()))
    }

    override fun removeNote(note: Note): Flow<Resource<Unit>> = flow {

        emit(Resource.Loading())
        try {
            val data = noteDao.removeNote(note.toNoteEntity())
            emit(Resource.Success(data))

        }catch (ioException: Exception){

            emit(Resource.Error(ioException.localizedMessage?:"Не известная ошибка"))
        }
       // emit(noteDao.removeNote(note.toNoteEntity()))
    }.flowOn(Dispatchers.IO)
}