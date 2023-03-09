package com.murat.noteapp_cleanarchitecture.presentation.ui.fragments.notefragment


import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.murat.noteapp_cleanarchitecture.R
import com.murat.noteapp_cleanarchitecture.databinding.FragmentNoteBinding
import com.murat.noteapp_cleanarchitecture.domain.model.Note
import com.murat.noteapp_cleanarchitecture.presentation.base.BaseFragment
import com.murat.noteapp_cleanarchitecture.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NoteFragment: BaseFragment(R.layout.fragment_note),NoteAdapter.OnclickListener {

    private var noteAdapter = NoteAdapter()
    private val viewBinding by viewBinding(FragmentNoteBinding::bind)
    private val viewModel: NoteViewModel by viewModels()


    override fun setupSubscribers() {

        viewModel.getAllNotesState.collectUIState(
           uiState = {
             viewBinding.progress.isVisible = it is UIState.Loading
           },

            onSuccess = {data->
                noteAdapter.addNote(data)
            }
        )

    }

    override fun setupRequest() {
        viewModel.getAllNotes()
    }

    override fun initClickListeners() {
        super.initClickListeners()

        val options = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .build()
        viewBinding.fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.addNoteFragment,null,options)

        }
    }


    override fun initialize() {
        super.initialize()
        setupRecyclerView()
        noteAdapter.setListener(this)
     }

    private fun setupRecyclerView(){
        viewBinding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            adapter = noteAdapter
        }
    }

    override fun onItemClick(note: Note) {
        findNavController().navigate(R.id.addNoteFragment, bundleOf("edit_note" to note))
    }
}
