package app.dsta

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import app.dsta.databinding.ActivityLoginBinding
import app.dsta.models.notes.Note
import app.dsta.models.users.ProfileViewModel
import app.dsta.models.users.User
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONException
import org.json.JSONObject


private const val TAG = "MainActivity"
class LoginActivity: AppCompatActivity() {
    private lateinit var callbackManager: CallbackManager
    private lateinit var loginButton: LoginButton

    private lateinit var binding: ActivityLoginBinding

    //login
    lateinit var etUser: EditText
    lateinit var etPass: EditText


    //room users
    private val mainViewModel: ProfileViewModel by viewModels()


    //firebase
   /* private lateinit var tvRedirectSignUp: TextView
    lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    lateinit var btnLogin: Button
    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth*/



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // View Bindings
        etUser = binding.editUsr
        etPass = binding.editPass



        // initialising Firebase auth object
        val database = Firebase.database
        val myRef = database.reference

        binding.btnLogin.setOnClickListener {


            if(!etPass.text.isNullOrEmpty() && !etUser.text.isNullOrEmpty()){
//firebase
                myRef.child("user").get().addOnSuccessListener { response ->
                   // Log.d("firebaseResponse",response.value.toString())


                    val resmap = response.value as ArrayList<Map<String, Any>>

                    Log.d("firebaseResponse",resmap.toString())
                    resmap.forEach{ card ->

                        if(!card.isNullOrEmpty()  && card["password"].toString() == etPass.text.toString() && card["email"].toString() == etUser.text.toString()){
                            Log.d("firebaseResponse", "Edit text: ${etUser.text}")

                            Log.d("firebaseResponse", "por clave: ${card["id"]}")

//room
                            mainViewModel.saveUser(
                                User(
                                    "${card["id"]}",
                                    "${card["name"]}",
                                    "${card["notes"]}")
                            )

                            Toast.makeText(this, "Logged user", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            //login()
                            finish()

                        }

                    }


                }.addOnFailureListener{

                    Log.e("firebaseResponse", "Error getting data")
                }



            }else{
                Toast.makeText(this, "Log In failed", Toast.LENGTH_SHORT).show()
            }




        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            // using finish() to end the activity
            //finish()
        }


//para checar cuantos user estan en room
        mainViewModel.getUsers()
        mainViewModel.savedUsers.observe(this, { usersList ->

            if(!usersList.isNullOrEmpty()){
                // on below line we are updating our list.


                for(user in usersList){
                    Log.d("userwithoutdelete",user.user_id)

                }

            }else{
                Log.d("userwithoutdelete","null or empty")
            }

        })








        //------>facebook<--------

        //init
         loginButton = findViewById(R.id.login_button)
         callbackManager = CallbackManager.Factory.create()
         loginButton.setPermissions(listOf("email", "user_birthday"))

         //login callback
         loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {

             override fun onSuccess(loginResult: LoginResult?) {
                 val userId = loginResult?.accessToken?.userId
                 Log.d(TAG, "onSuccess: userId $userId")

                 val bundle = Bundle()
                 bundle.putString("fields", "id, email, first_name, last_name, gender,age_range")


                 //Graph API to access the data of user's facebook account
                 val request = GraphRequest.newMeRequest(
                     loginResult?.accessToken
                 ) { fbObject, response ->
                     Log.v("Login Success", response.toString())


                     //For safety measure enclose the request with try and catch
                     try {

                         Log.d(TAG, "onSuccess: fbObject $fbObject")

                         val firstName = fbObject.getString("first_name")
                         val lastName = fbObject.getString("last_name")
                         val gender = fbObject.getString("gender")
                         val email = fbObject.getString("email")

                         Log.d(TAG, "onSuccess: firstName $firstName")
                         Log.d(TAG, "onSuccess: lastName $lastName")
                         Log.d(TAG, "onSuccess: gender $gender")
                         Log.d(TAG, "onSuccess: email $email")

                     } //If no data has been retrieve throw some error
                     catch (e: JSONException) {

                     }

                 }
                 //Set the bundle's data as Graph's object data
                 request.setParameters(bundle)

                 //Execute this Graph request asynchronously
                 request.executeAsync()

             }

             override fun onCancel() {
                 Log.d(TAG, "onCancel: called")
             }

             override fun onError(error: FacebookException?) {
                 Log.d(TAG, "onError: called")
             }
         })



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

    }

    //funcions firebase


    private fun login() {
        val email = etUser.text.toString()
        val pass = etPass.text.toString()
        // calling signInWithEmailAndPassword(email, pass)
        // function using Firebase auth object
        // On successful response Display a Toast


        val database = Firebase.database
        val myRef = database.reference

        myRef.child("user").get().addOnSuccessListener { response ->

            val resmap = response.value as ArrayList<Map<String, Any>>
            var myarrayjson: Array<JSONObject> = arrayOf()

            Log.d("firebaseResponse",resmap.toString())
            resmap.forEach{ user ->

                if(!user.isNullOrEmpty()  && user["email"].toString() == email){
                    Log.d("firebaseResponse", "por clave: ${user["email"]}  y ${user["password"]}")
                    //val myjson = JSONObject(card)

                    //val intent = Intent(this, MainActivity::class.java)
                    //startActivity(intent)

                    //myarrayjson += arrayOf(myjson)
                }else{

                    Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()

                }

            }


        }


        /*auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }*/
    }


    //firebase proyecto de la clase del profe

    /*
        // Write a message to the database
        val database = Firebase.database
        //val myRef = database.getReference("examinar")
        val myRef = database.reference
        myRef.child("movie").child("Title").setValue("Endgame")
        myRef.child("movie").child("Year").setValue("2019")
        myRef.child("movie").child("imdbID").setValue("tt4154796")
        myRef.child("movie").child("Type").setValue("movie")
        myRef.child("movie").child("Poster").setValue("https://m.media-amazon.com/images/M/MV5BNGZiMzBkZjMtNjE3Mi00MWNlLWIyYjItYTk3MjY0Yjg5ODZkXkEyXkFqcGdeQXVyNDg4NjY5OTQ@._V1_SX300.jpg")
        myRef.child("movie").child("Country").setValue("US")
        myRef.child("movie").child("Gender").setValue("Action")
                myRef.child("movie").get().addOnSuccessListener { response ->
                    Log.d("firebaseResponse",response.value.toString())


                    val resmap = response.value as ArrayList<Map<String, Any>>
                    var myarrayjson: Array<JSONObject> = arrayOf()

                    Log.d("firebaseResponse",resmap.toString())
                    resmap.forEach{ card ->

                        if(!card.isNullOrEmpty()){
                            Log.d("firebaseResponse", "por clave: ${card}")
                           val myjson = JSONObject(card)

                            myarrayjson += arrayOf(myjson)
                        }

                    }


                    //metodo 1

                   /*val jsonArray = JSONArray(response.value.toString())
                    var myarrayjson: Array<JSONObject> = arrayOf()

                    for (i in 1 until jsonArray.length()) {

                        Log.e("firebaseResponse",jsonArray.length().toString())



                        if(jsonArray[i].toString() != null){


                       val myjson = JSONObject(jsonArray[i].toString())

                        myarrayjson += arrayOf(myjson)
                        }


                    }*/

                    //Log.d("firebaseResponse",myjson.toString())

                    binding.notesRV.adapter = MovieAdapter(this,myarrayjson)

                }.addOnFailureListener{
                    Log.e("firebaseResponse", "Error getting data")
                }








                binding.idFAB.setOnClickListener {
                    // adding a click listener for fab button
                    // and opening a new intent to add a new note.
                    val intent = Intent(this@MainActivity, AddActivity::class.java)
                    startActivity(intent)
                    this.finish()
                }


    }*/

}