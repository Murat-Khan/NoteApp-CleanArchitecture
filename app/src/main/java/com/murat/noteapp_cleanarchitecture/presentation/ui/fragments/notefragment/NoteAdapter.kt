package com.murat.noteapp_cleanarchitecture.presentation.ui.fragments.notefragment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.murat.noteapp_cleanarchitecture.databinding.NoteItemBinding
import com.murat.noteapp_cleanarchitecture.domain.model.Note
import java.util.*
import kotlin.collections.List

class NoteAdapter:RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var noteList = arrayListOf<Note>()

     fun addNote(note: List<Note>){
         noteList.clear()
        noteList.addAll(note)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
      return NoteViewHolder(
          NoteItemBinding.inflate(
              LayoutInflater.from(parent.context)
              ,parent,false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       holder.onBind(noteList[position])
    }

    override fun getItemCount(): Int {
       return noteList.size
    }

    class NoteViewHolder(private val binding: NoteItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun onBind(note: Note) {
            binding.tvNoteTitle.text = note.title
            binding.tvDescription.text = note.description

            val random = Random()
            val color =
                Color.argb(
                    255, random.nextInt(256),
                    random.nextInt(256), random.nextInt(256)
                )
            binding.ibColor.setBackgroundColor(color)
        }

    }
}