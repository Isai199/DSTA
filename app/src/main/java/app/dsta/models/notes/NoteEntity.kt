package app.dsta.models.notes

import androidx.room.*
import app.dsta.room.TABLE_NOTES


@Entity(tableName = TABLE_NOTES)
data class NoteEntity(


    @ColumnInfo(name = "note_id") @PrimaryKey val uuid: String,
    //@ColumnInfo(name = "note_id") @PrimaryKey(autoGenerate = true) val uuid: Int = 0

    @ColumnInfo(name = "note_title") val title: String,
    val note_duedate: String,
    val note_startdate: String,
    val note_state: String,
    val note_description: String,
    val note_iduser: String,
    )


fun NoteEntity.toNote() = Note(
    uuid,
    title,
    note_duedate,
    note_startdate,
    note_state,
    note_description,
    note_iduser
)


/*@Entity(tableName = TABLE_USERS)
data class UserEntity(

    @ColumnInfo(name = "user_id") @PrimaryKey val uuid: String,
    @ColumnInfo(name = "user_name") val name: String,
    val user_height: Double,
    val user_weight: Double,
    val user_email: String,
    val user_password: String


)

fun UserEntity.toUser() = User(
    uuid,
    name,
    user_height,
    user_weight,
    user_email,
    user_password
)*/




