package com.murat.noteapp_cleanarchitecture.presentation.ui.fragments.notefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murat.noteapp_cleanarchitecture.domain.model.Note
import com.murat.noteapp_cleanarchitecture.domain.usecase.GetAllNotesUseCase
import com.murat.noteapp_cleanarchitecture.domain.utils.Resource
import com.murat.noteapp_cleanarchitecture.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase
) : ViewModel() {

    private val _getAllNotesState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    // val getAllNotesState :StateFlow<UIState<List<Note>>> = _getAllNotesState

    val getAllNotesState = _getAllNotesState.asStateFlow()

    fun getAllNotes() {

        viewModelScope.launch(Dispatchers.IO) {
            getAllNotesUseCase.getAllNotes().collect { res ->
                when (res) {
                    is Resource.Error -> {
                        if (res.message != null) {
                            _getAllNotesState.value = UIState.Error(res.message)
                        }
                    }
                    is Resource.Loading -> {
                        _getAllNotesState.value = UIState.Loading()
                    }
                    is Resource.Success -> {
                        if (res.data != null) {
                            _getAllNotesState.value = UIState.Success(res.data)
                        }
                    }
                }
            }
        }
    }
}