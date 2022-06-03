package app.dsta.retrofit

import com.google.gson.annotations.SerializedName

data class SpecialistEntry(
    @SerializedName("id")
    val id: Int,
    @SerializedName("first_name")
    val first_name: String,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("social_insurance_number")
    val phone: Int,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("address")
    val address: AddressEntry,

)

data class AddressEntry(
    @SerializedName("city")
    val city: String
)



/*data class PokemonEntry(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("types")
    val types: List<TypeEntry> ,
    @SerializedName("stats")
    val stats: List<StatsEntry>,
    @SerializedName("sprites")
    val sprites: SpriteEntry
)


data class  TypeEntry(
    @SerializedName("type")
    val type: TypeName
)
data class TypeName(

    @SerializedName("name")
    val name: String
)

data class StatsEntry(
    @SerializedName("base_stat")
    val base_stat: Int,
    @SerializedName("stat")
    val stat: StatEntry
)

data class StatEntry(
    @SerializedName("name")
    val stat_value: String
)

data class SpriteEntry(
    @SerializedName("front_default")
    val sprite_value: String
)*/
