package com.murat.noteapp_cleanarchitecture.presentation.ui.fragments.notefragment


import com.murat.noteapp_cleanarchitecture.domain.model.Note
import com.murat.noteapp_cleanarchitecture.domain.usecase.EditNoteUseCase
import com.murat.noteapp_cleanarchitecture.domain.usecase.GetAllNotesUseCase
import com.murat.noteapp_cleanarchitecture.domain.usecase.RemoveNoteUseCase
import com.murat.noteapp_cleanarchitecture.presentation.base.BaseViewModel
import com.murat.noteapp_cleanarchitecture.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val removeNoteUseCase: RemoveNoteUseCase
) : BaseViewModel() {

    private val _getAllNotesState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    val getAllNotesState = _getAllNotesState.asStateFlow()



    private val _removeNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val removeNote = _removeNoteState.asStateFlow()

    fun getAllNotes() {
        getAllNotesUseCase().collectFlow(_getAllNotesState)
    }



    fun removeNote(note: Note){
        removeNoteUseCase.removeNote(note)
    }
}