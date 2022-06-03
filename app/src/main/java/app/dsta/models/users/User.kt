package app.dsta.models.users



class User(

    user_id: String,
    user_name: String,
    user_notes: String

) {
    val user_id : String = user_id
    val user_name: String= user_name
    val user_notes: String = user_notes
}

fun User.toEntity() = UserEntity(
    user_id,
    user_name,
    user_notes

)

