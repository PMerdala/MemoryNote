package pl.merdala.memorynote.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.merdala.core.data.Note
import pl.merdala.memorynote.R
import pl.merdala.memorynote.databinding.ItemNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class NotesListAdapter(private var notes: ArrayList<Note>) :
    RecyclerView.Adapter<NotesListAdapter.NoteViewHolder>() {

    fun updateNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NoteViewHolder(
        inflateItemNoteBinding(parent)
    )

    private fun inflateItemNoteBinding(parent: ViewGroup) =
        ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(note: Note) {
            binding.title.text = note.title
            binding.content.text = note.content
            val sdf = SimpleDateFormat(
                binding.root.context.getString(R.string.item_note_last_update_date_format)
            )
            val lastUpdateDate = Date(note.updateDate)
            binding.updateDate.text =
                binding.updateDate.context.getString(R.string.last_update).format(
                    sdf.format(
                        lastUpdateDate
                    )
                )
        }
    }

}