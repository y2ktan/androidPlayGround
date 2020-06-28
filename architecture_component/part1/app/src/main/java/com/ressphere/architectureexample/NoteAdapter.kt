package com.ressphere.architectureexample

import kotlinx.android.synthetic.main.note_item.text_view_priority
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter: RecyclerView.Adapter<NoteHolder>() {
    private val notes = ArrayList<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(
               R.layout.note_item, parent, false)
        return NoteHolder(itemView)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote = notes[position]
        holder.textViewTitle.text = currentNote.title
        holder.textViewPriority.text = currentNote.priority.toString()
        holder.textViewDescription.text = currentNote.description

    }

    fun setNotes(notes:List<Note>){
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }
}

class NoteHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val textViewTitle:TextView = view.text_view_title
    val textViewDescription:TextView = view.text_view_description
    val textViewPriority:TextView = view.text_view_priority
}

