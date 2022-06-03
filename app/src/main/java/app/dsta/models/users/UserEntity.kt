package app.dsta.models.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.dsta.room.TABLE_USERS


@Entity(tableName = TABLE_USERS)
data class UserEntity(
    @ColumnInfo(name = "user_id") @PrimaryKey val uuid: String,
    @ColumnInfo(name = "user_name") val user_name: String,
    val user_notes: String,
)

fun UserEntity.toUser()  = User(
    uuid,
    user_name,
    user_notes
)
