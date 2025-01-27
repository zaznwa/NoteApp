package com.geeks.noteapp.ui.adapters

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geeks.noteapp.data.models.NoteModel
import com.geeks.noteapp.databinding.ItemNoteBinding

class NoteAdapter : ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallBack()) {
    private val notesList = mutableListOf<NoteModel>()

    fun addNoteToTop(note: NoteModel) {
        if (notesList.isEmpty()) {
            notesList.addAll(currentList)
        }
        notesList.add(0, note)
        submitList(notesList.toList())
    }
    class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) {
            binding.txtTitle.text = item.title
            binding.txtDescription.text = item.description
            binding.txtDate.text = item.date
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class DiffCallBack : DiffUtil.ItemCallback<NoteModel>() {

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }

    }
}