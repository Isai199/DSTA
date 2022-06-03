package app.dsta.models.notes


import androidx.room.*
import app.dsta.room.TABLE_NOTES


@Dao
interface NoteDao {
    @Query("SELECT * FROM $TABLE_NOTES")
    fun getNotesFromdatabase(): List<NoteEntity>

    @Query("SELECT * FROM $TABLE_NOTES where note_id = :uuid")
    fun getNoteByUuid(uuid: String): NoteEntity

    @Delete
    fun delete(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(note: NoteEntity)

}


/*@Dao
interface UserDao {
    @Query("SELECT * FROM $TABLE_USERS")
    fun getUsersFromdatabase(): List<UserEntity>

    @Query("SELECT * FROM $TABLE_USERS where user_id = :uuid")
    fun getUserByUuid(uuid: String): UserEntity

    @Delete
    fun delete(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: UserEntity)
}*/

