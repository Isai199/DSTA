package app.dsta.room


import androidx.room.Database
import androidx.room.RoomDatabase
import app.dsta.models.notes.NoteDao
import app.dsta.models.notes.NoteEntity
import app.dsta.models.users.UserDao
import app.dsta.models.users.UserEntity


const val DATABASE_VERSION = 2
const val TABLE_NOTES = "notes"
const val TABLE_USERS = "users"
const val DATABASE_NAME = "appdatabase.sqlite"

@Database(entities = [NoteEntity::class, UserEntity::class],
    version = DATABASE_VERSION
)
abstract class AppDataBase : RoomDatabase(){

    abstract fun noteDao(): NoteDao


    abstract fun userDao(): UserDao


}


