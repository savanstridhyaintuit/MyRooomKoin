package com.savan.myroomkoin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.savan.myroomkoin.databinding.ItemListnotesBinding
import com.savan.myroomkoin.entity.Note

class NoteAdapter constructor(private val listTodos: ArrayList<Note>) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemListnotesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.txtTitle.text = note.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemListnotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = listTodos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note: Note = listTodos[position]
        holder.bind(note)
    }


    fun setNoteList(listTodosData: List<Note>) {
        listTodos.addAll(listTodosData)
        notifyDataSetChanged()
    }
}