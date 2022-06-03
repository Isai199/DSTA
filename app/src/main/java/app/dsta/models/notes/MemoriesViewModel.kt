package app.dsta.models.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.dsta.room.DataBaseManager
import app.dsta.room.MyCoroutines
import kotlinx.coroutines.launch

class MemoriesViewModel: ViewModel() {

    fun saveNote(note: Note){
        viewModelScope.launch{
            val noteDao = DataBaseManager.instance.database.noteDao()
            MyCoroutines(noteDao).save(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch{
            val noteDao = DataBaseManager.instance.database.noteDao()
            MyCoroutines(noteDao).delete(note)
        }
    }

    //val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    val savedNotes = MutableLiveData<List<Note>>()



    fun getNotes(){
        viewModelScope.launch{
            val noteDao = DataBaseManager.instance.database.noteDao()
            savedNotes.value = MyCoroutines(noteDao).getNotes().value
        }
    }
}