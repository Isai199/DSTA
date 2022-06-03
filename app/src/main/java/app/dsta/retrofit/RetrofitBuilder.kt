package app.dsta.retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitBuilder {
    @GET("users/random_user")
    fun getSpecialistById(): Call<SpecialistEntry>

    companion object{
        private val BASE_URL = "https://random-data-api.com/api/"

        fun create() : RetrofitBuilder{
            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(RetrofitBuilder::class.java)
        }
    }
}