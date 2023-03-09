package com.murat.noteapp_cleanarchitecture.presentation.ui.fragments.addnotefragment


import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.murat.noteapp_cleanarchitecture.R
import com.murat.noteapp_cleanarchitecture.databinding.FragmentAddNoteBinding
import com.murat.noteapp_cleanarchitecture.domain.model.Note
import com.murat.noteapp_cleanarchitecture.presentation.base.BaseFragment
import com.murat.noteapp_cleanarchitecture.presentation.utils.UIState
import com.murat.noteapp_cleanarchitecture.presentation.utils.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddNoteFragment : BaseFragment(R.layout.fragment_add_note) {

    private val viewModel: AddNoteViewModel by viewModels()
    private val viewBinding by viewBinding(FragmentAddNoteBinding::bind)
    private var note: Note? = null


    override fun initClickListeners() {
        super.initClickListeners()
        viewBinding.btnSave.setOnClickListener {
            if (viewBinding.etNoteDesc.text.isEmpty() || viewBinding.etNoteTitle.text.isEmpty()) {

                requireActivity().showToast("заполните поля")

            } else {
                createNote()
            }
        }
    }

    private fun createNote() {
        viewModel.addNote(
            Note(
                title = viewBinding.etNoteTitle.text.toString(),
                description = viewBinding.etNoteDesc.text.toString()
            )
        )
    }

    override fun setupSubscribers() {
        viewModel.addNoteState.collectUIState(
            uiState = {
                viewBinding.progressBar.isVisible = it is UIState.Loading
            },

            onSuccess = {

                findNavController().navigateUp()

            }
        )

        viewModel.editNote.collectUIState(
            uiState = {
                viewBinding.progressBar.isVisible = it is UIState.Loading
            },

            onSuccess = {
                editNote()
                findNavController().navigateUp()
            }
        )


    }

    override fun initialize() {
        super.initialize()
        getData()
    }

    private fun getData() {

        arguments?.let {
            val value = it.getSerializable("edit_note")
            if (value != null) {
                note = value as Note
            }
        }
            viewBinding.etNoteDesc.setText(note?.description)
            viewBinding.etNoteTitle.setText(note?.title)
            viewBinding.btnSave.text = "Update"
        }

        private fun editNote() {

            viewModel.editNote(
                Note(
                    title = viewBinding.etNoteTitle.text.toString(),
                    description = viewBinding.etNoteDesc.text.toString()

                )
            )
        }


    }