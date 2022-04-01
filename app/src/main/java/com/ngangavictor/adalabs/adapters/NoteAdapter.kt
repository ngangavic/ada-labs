package com.ngangavictor.adalabs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ngangavictor.adalabs.databinding.RowViewNoteBinding
import com.ngangavictor.adalabs.models.NoteModel

class NoteAdapter(
    private val list: ArrayList<NoteModel>,
    var noteItemClickListener: NoteItemClickListener
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

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
        private val binding: RowViewNoteBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(item: NoteModel) {
            binding.apply {
                binding.textViewTitle.text = item.title
                binding.textViewNote.text = item.body
//                binding.root.setOnClickListener(this@NoteViewHolder)
            }
        }


        override fun onClick(p0: View?) {
            if (p0 != null) {
                noteItemClickListener.itemClicked(p0, list[position])
            }
        }
    }

    interface NoteItemClickListener {
        fun itemClicked(view: View, noteModel: NoteModel)
    }
}