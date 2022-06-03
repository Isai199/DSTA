package app.dsta.adapters.memories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import app.dsta.MemoriesFragment
import app.dsta.R
import app.dsta.models.notes.Note

class MemoriesAdapter(
    val context: MemoriesFragment,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickShowInterface: NoteClickShowInterface
):
    RecyclerView.Adapter<MemoriesAdapter.ViewHolder>() {

    // on below line we are creating a
    // variable for our all notes list.
    private val allNotes = ArrayList<Note>()


    // on below line we are creating a view holder class.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are creating an initializing all our
        // variables which we have added in layout file.
        val noteTV = itemView.findViewById<TextView>(R.id.tvReminderTitle)
        val duedateTV = itemView.findViewById<TextView>(R.id.tvReminderDuedate)
        val stateTV = itemView.findViewById<TextView>(R.id.tvReminderState)
        val seereminderBtn = itemView.findViewById<TextView>(R.id.btnReminderSeeReminder)

        val deletereminderBtn = itemView.findViewById<Button>(R.id.btnReminderDelete)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflating our layout file for each item of recycler view.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_reminder,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // on below line we are setting data to item of recycler view.
        holder.noteTV.setText("Titulo: "+allNotes.get(position).note_title)
        holder.duedateTV.setText("Fecha limite: "+allNotes.get(position).note_duedate)
        holder.stateTV.setText("Estado: "+allNotes.get(position).note_state)


        holder.seereminderBtn.setOnClickListener {
            noteClickShowInterface.onShowIconClick(allNotes.get(position))
        }


        holder.deletereminderBtn.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }



    }


    override fun getItemCount(): Int {
        // on below line we are
        // returning our list size.
        return allNotes.size
    }


    // below method is use to update our list of notes.
    fun updateList(newList: List<Note>) {
        // on below line we are clearing
        // our notes array list
        allNotes.clear()
        // on below line we are adding a
        // new list to our all notes list.
        allNotes.addAll(newList)
        // on below line we are calling notify data
        // change method to notify our adapter.
        notifyDataSetChanged()
    }


    interface NoteClickDeleteInterface {
        // creating a method for click
        // action on delete image view.
        fun onDeleteIconClick(note: Note)
    }


    interface NoteClickShowInterface{

        fun onShowIconClick(note:Note)
    }





}


