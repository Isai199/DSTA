package app.dsta

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import app.dsta.databinding.FragmentProfileBinding
import app.dsta.models.users.ProfileViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    //room users
    private val mainViewModel: ProfileViewModel by viewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // initialising Firebase auth object
        val database = Firebase.database
        val myRef = database.reference



        //room para id

        mainViewModel.getUsers()
        mainViewModel.savedUsers.observe(viewLifecycleOwner, { usersList ->

            if(!usersList.isNullOrEmpty()){
                // on below line we are updating our list.


                for(user in usersList){
                    Log.d("thesearethenotes",user.user_id)



                    myRef.child("user").child("${user.user_id}").child("username").get().addOnSuccessListener { response ->
                        binding.tvProfileUsername.setText("${response.value.toString()}")

                        Log.d("firebaseResponse",response.value.toString())
                    }

                    myRef.child("user").child("${user.user_id}").child("name").get().addOnSuccessListener { response ->
                        binding.tvProfileName.setText("${response.value.toString()}")

                        Log.d("firebaseResponse",response.value.toString())
                    }

                    myRef.child("user").child("${user.user_id}").child("email").get().addOnSuccessListener { response ->
                        binding.tvProfileEmail.setText("${response.value.toString()}")

                        Log.d("firebaseResponse",response.value.toString())
                    }
                    myRef.child("user").child("${user.user_id}").child("patient").get().addOnSuccessListener { response ->
                        binding.tvProfilePatient.setText("${response.value.toString()}")

                        Log.d("firebaseResponse",response.value.toString())
                    }
                    myRef.child("user").child("${user.user_id}").child("notes").get().addOnSuccessListener { response ->
                        binding.tvProfileNotes.setText("${response.value.toString()}")

                        Log.d("firebaseResponse",response.value.toString())
                    }
                    myRef.child("user").child("${user.user_id}").child("phone").get().addOnSuccessListener { response ->
                        binding.tvProfilePhone.setText("${response.value.toString()}")

                        Log.d("firebaseResponse",response.value.toString())
                    }

                }

            }else{
                Log.d("thesearethenotes","null or empty")
            }

        })




        myRef.child("user").child("1").get().addOnSuccessListener { response ->
            Log.d("firebaseResponse",response.value.toString())


            //val resmap = response.value as ArrayList<Map<String, Any>>
            /*var myarrayjson: Array<JSONObject> = arrayOf()

            Log.d("firebaseResponse",resmap.toString())
            resmap.forEach{ user ->

                if(!user.isNullOrEmpty()){
                    Log.d("firebaseResponse", "por clave: ${user}")
                    val myjson = JSONObject(user)

                    myarrayjson += arrayOf(myjson)
                }

            }*/
        }





        // Inflate the layout for this fragment
        return root
    }


}


/*interface RetrofitBuilder {
    @GET("pokemon/{id}")
    fun getPokemonById(@Path("id") id:String): Call<PokemonEntry>

    companion object{
        private val BASE_URL = "https://pokeapi.co/api/v2/"

        fun create() : RetrofitBuilder{
            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(RetrofitBuilder::class.java)
        }
    }
}*/