package app.dsta.models.users

import androidx.room.*
import app.dsta.room.TABLE_USERS

@Dao
interface UserDao {

    @Query("SELECT * FROM $TABLE_USERS")
    fun getUserFromdatabase(): List<UserEntity>

    @Query("SELECT * FROM $TABLE_USERS where user_id = :uuid")
    fun getUserByUuid(uuid: String): UserEntity

    @Delete
    fun deleteUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: UserEntity)

}