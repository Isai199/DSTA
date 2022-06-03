package app.dsta.room


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.dsta.models.notes.Note
import app.dsta.models.notes.NoteDao
import app.dsta.models.notes.toEntity
import app.dsta.models.notes.toNote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyCoroutines(private val noteDao: NoteDao) {

    suspend fun save(note: Note) = withContext(Dispatchers.IO){
        noteDao.save(note.toEntity())
    }

    suspend fun delete(note: Note) = withContext(Dispatchers.IO){
        noteDao.delete(note.toEntity())
    }

    suspend fun getNotes(): LiveData<List<Note>> = withContext(Dispatchers.IO){
        return@withContext MutableLiveData(noteDao.getNotesFromdatabase().map {eachNoteEntity ->
            eachNoteEntity.toNote() })

    }
}





