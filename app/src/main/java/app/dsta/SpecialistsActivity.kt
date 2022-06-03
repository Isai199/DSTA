package app.dsta

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import app.dsta.adapters.specialists.SpecialistAdapter
import app.dsta.databinding.ActivitySpecialistsBinding
import app.dsta.models.users.ProfileViewModel
import app.dsta.retrofit.RetrofitBuilder
import app.dsta.retrofit.SpecialistEntry
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SpecialistsActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySpecialistsBinding

    //room users
    private val mainViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySpecialistsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialising Firebase auth object
        val database = Firebase.database
        val myRef = database.reference



        val retrofit = RetrofitBuilder.create().getSpecialistById()

        retrofit.enqueue(object: Callback<SpecialistEntry> {
            override fun onResponse(
                call: Call<SpecialistEntry>,
                response: Response<SpecialistEntry>
            ) {
                val resBody = response.body()
                if(resBody != null){

                    Log.d("retrofitresponse","res: ${resBody}")
                    Log.d("retrofitresponse","name: ${resBody.first_name}")

                    var myarrayjson: Array<JSONObject> = arrayOf()
                    //room user
                    mainViewModel.getUsers()
                    mainViewModel.savedUsers.observe(this@SpecialistsActivity, { usersList ->

                        if (!usersList.isNullOrEmpty()) {
                            // on below line we are updating our list.


                            for (user in usersList) {
                                Log.d("thesearethenotes", user.user_id)

                                    myarrayjson=    arrayOf(JSONObject("{\"id\": \"${resBody.id}\", \"first_name\": \"${resBody.first_name}\", \"last_name\": \"${resBody.last_name}\", \"email\": \"${resBody.email}\", \"phone\": \"${resBody.phone}\", \"city\": \"${resBody.address.city}\", \"avatar\": \"${resBody.avatar}\", \"iduser\": \"${user.user_id}\"}"))

                            }
                        }
                        binding.rvReminderSpecialistRandom.adapter = SpecialistAdapter(myarrayjson)

                    })





                }


            }

            override fun onFailure(call: Call<SpecialistEntry>, t: Throwable) {
                Log.e("retofitresponse", "error: ${t.message}")
            }


        })

        //room user
        mainViewModel.getUsers()
        mainViewModel.savedUsers.observe(this@SpecialistsActivity, { usersList ->

            if (!usersList.isNullOrEmpty()) {
                // on below line we are updating our list.


                for (user in usersList) {

                    //on firebase

                    myRef.child("user").child("${user.user_id}").child("specialist").get().addOnSuccessListener { response ->
                        Log.d("firebaseResponse", response.value.toString())



                                        if(response.value != null) {
                                            val resmap = response.value as ArrayList<Map<String, Any>>
                                            var myarrayjson: Array<JSONObject> = arrayOf()

                                            Log.d("firebaseResponse", resmap.toString())
                                            resmap.forEach { card ->

                                                if (!card.isNullOrEmpty()) {
                                                    Log.d("firebaseResponse", "por clave: ${card}")
                                                    val myjson = JSONObject(card)

                                                    myarrayjson += arrayOf(myjson)
                                                }

                                            }



    binding.rvReminderSpecialistFavorites.adapter = SpecialistAdapter(myarrayjson)
}
                    }.addOnFailureListener {
                        Log.e("firebaseResponse", "Error getting data")
                    }

                }
            }


        })





        

        



    }
}


/*val resmap = response.value as ArrayList<Map<String, Any>>
                    var myarrayjson: Array<JSONObject> = arrayOf()

                    Log.d("firebaseResponse",resmap.toString())
                    resmap.forEach{ card ->

                        if(!card.isNullOrEmpty()){
                            Log.d("firebaseResponse", "por clave: ${card}")
                           val myjson = JSONObject(card)

                            myarrayjson += arrayOf(myjson)
                        }

                    }*/