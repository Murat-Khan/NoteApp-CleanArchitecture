package com.murat.noteapp_cleanarchitecture.presentation.ui.fragments.notefragment

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.murat.noteapp_cleanarchitecture.R
import com.murat.noteapp_cleanarchitecture.data.model.NoteEntity

import com.murat.noteapp_cleanarchitecture.databinding.FragmentNoteBinding
import com.murat.noteapp_cleanarchitecture.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private var noteAdapter = NoteAdapter()
    private lateinit var viewBinding: FragmentNoteBinding
    private val viewModel: NoteViewModel by viewModels()
    private var note: ArrayList <Note>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNoteBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSubscribes()
        setupRequests()
        initClicker()
        setUpRecyclerView()
    }

    private fun setupSubscribes() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getAllNotesState.collect { state ->
                    when (state) {
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        is UIState.Loading -> {
                            // TODO show progress bar
                        }
                        is UIState.Success -> {

                            noteAdapter.addNote(state.data)


                        }
                    }
                }
            }
        }
    }

    private fun setupRequests() {
        viewModel.getAllNotes()
    }

    private fun initClicker() {
        viewBinding.fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.addNoteFragment)
        }
    }

    private fun setUpRecyclerView() {
        viewBinding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            adapter = noteAdapter
        }
    }


}
