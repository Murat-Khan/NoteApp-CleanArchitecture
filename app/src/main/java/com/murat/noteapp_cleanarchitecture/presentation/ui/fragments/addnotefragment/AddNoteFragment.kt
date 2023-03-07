package com.murat.noteapp_cleanarchitecture.presentation.ui.fragments.addnotefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.murat.noteapp_cleanarchitecture.R
import com.murat.noteapp_cleanarchitecture.data.local.NoteDatabase
import com.murat.noteapp_cleanarchitecture.data.model.NoteEntity
import com.murat.noteapp_cleanarchitecture.databinding.FragmentAddNoteBinding
import com.murat.noteapp_cleanarchitecture.domain.model.Note
import com.murat.noteapp_cleanarchitecture.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private val viewModel: AddNoteViewModel by viewModels()
    private lateinit var viewBinding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        initClicker()


    }

    private fun initClicker() {
        viewBinding.btnSave.setOnClickListener {
            setupRequests()
            findNavController().navigateUp()
        }
    }



    private fun setupRequests() {
        viewModel.addNote(
            Note(
                title = viewBinding.etNoteTitle.text.toString(),
                description = viewBinding.etNoteDesc.text.toString()
            )
        )


    }


}