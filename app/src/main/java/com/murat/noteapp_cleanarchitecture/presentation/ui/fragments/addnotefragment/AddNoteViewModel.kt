package com.murat.noteapp_cleanarchitecture.presentation.ui.fragments.addnotefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murat.noteapp_cleanarchitecture.domain.model.Note
import com.murat.noteapp_cleanarchitecture.domain.usecase.AddNoteUseCase
import com.murat.noteapp_cleanarchitecture.domain.utils.Resource
import com.murat.noteapp_cleanarchitecture.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddNoteViewModel@Inject constructor(
    private val addNoteUseCase : AddNoteUseCase
) : ViewModel() {

    private val _addNoteState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())

    fun addNote(note: Note) {

        viewModelScope.launch(Dispatchers.IO) {
            addNoteUseCase.addNote(note).collect { res ->
                when (res) {
                    is Resource.Error -> {
                        if (res.message != null) {
                            _addNoteState.value = UIState.Error(res.message)
                        }
                    }
                    is Resource.Loading -> {
                        _addNoteState.value = UIState.Loading()
                    }
                    is Resource.Success -> {
                        if (res.data != null) {
                            UIState.Success(res.data)
                        }
                    }
                }
            }
        }
    }
}