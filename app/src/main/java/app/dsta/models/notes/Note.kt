package app.dsta.models.notes

import java.io.Serializable


class Note (
    note_id: String,
    note_title: String,
    note_duedate: String,
    note_startdate: String,
    note_state: String,
    note_description: String,
    note_iduser: String
): Serializable {


    val note_id : String = note_id
    val note_title: String= note_title
    val note_duedate: String = note_duedate
    val note_startdate: String = note_startdate
    val note_state: String = note_state
    val note_description: String = note_description
    val note_iduser: String = note_iduser

}

fun Note.toEntity() = NoteEntity(
    note_id,
    note_title,
    note_duedate,
    note_startdate,
    note_state,
    note_description,
    note_iduser

)

