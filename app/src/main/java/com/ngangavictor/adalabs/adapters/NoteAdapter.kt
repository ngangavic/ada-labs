package com.ngangavictor.adalabs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ngangavictor.adalabs.R
import com.ngangavictor.adalabs.databinding.RowViewNoteBinding
import com.ngangavictor.adalabs.models.NoteModel

class NoteAdapter(private val list:ArrayList<NoteModel>): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteViewHolder {

        return NoteViewHolder(
            RowViewNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

   inner class NoteViewHolder constructor(
       private val binding: RowViewNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) {
            binding.apply {

            }
        }
    }
}