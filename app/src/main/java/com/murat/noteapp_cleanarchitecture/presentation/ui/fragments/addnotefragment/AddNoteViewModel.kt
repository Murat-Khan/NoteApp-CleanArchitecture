package com.murat.noteapp_cleanarchitecture.presentation.ui.fragments.addnotefragment


import com.murat.noteapp_cleanarchitecture.domain.model.Note
import com.murat.noteapp_cleanarchitecture.domain.usecase.AddNoteUseCase
import com.murat.noteapp_cleanarchitecture.domain.usecase.EditNoteUseCase
import com.murat.noteapp_cleanarchitecture.presentation.base.BaseViewModel
import com.murat.noteapp_cleanarchitecture.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class AddNoteViewModel@Inject constructor(
    private val addNoteUseCase : AddNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase,
) : BaseViewModel() {


    private val _addNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val addNoteState = _addNoteState.asStateFlow()

    private val _editNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val editNote = _editNoteState.asStateFlow()

    fun addNote(note: Note) {
        addNoteUseCase(note).collectFlow(_addNoteState)
    }

    fun editNote(note: Note) {
        editNoteUseCase.editNote(note).collectFlow(_editNoteState)
    }
}