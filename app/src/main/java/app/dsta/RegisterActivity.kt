package app.dsta

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.dsta.databinding.ActivityRegisterBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONArray

class RegisterActivity: AppCompatActivity() {
    //binding
    private lateinit var binding: ActivityRegisterBinding


    //register
    lateinit var etEmail: EditText
    lateinit var etConfPass: EditText
    private lateinit var etPass: EditText
    private lateinit var btnSignUp: Button
    lateinit var tvRedirectLogin: TextView

    // create Firebase authentication object
    //private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

         // View Bindings
        etEmail = binding.etSEmailAddress
        etConfPass = binding.etConfPassword
        etPass = binding.etPassword
        btnSignUp = binding.btnSSigned
        tvRedirectLogin = binding.tvRedirectSignUp

        // Initialising auth object
        //auth = Firebase.auth

        btnSignUp.setOnClickListener {

            signUpUser()
        }

        // switching from signUp Activity to Login Activity
        tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }

    private fun signUpUser() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confirmPassword = etConfPass.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }
        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        val database = Firebase.database
        //val myRef = database.getReference("examinar")
        val myRef = database.reference
        val alphabet = 'a'..'z'


        myRef.child("movie").get().addOnSuccessListener { response ->

            val jsonArray = JSONArray(response.value.toString())
            val idNumber = jsonArray.length()
            //myRef.child("movie").child("$idNumber").setValue(Movie("$noteTitle","$noteYear","$noteType","$noteCountry","$noteGender","$noteLink"))


        }


        //Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
    }



        /*auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }*/

}


//registrar firebase ejemplo del profe
/*binding.idBtn.setOnClickListener {
            // on below line we are getting
            // title and desc from edit text.
            val noteTitle = binding.idEdtTitle.text.toString()
            val noteYear =binding.idEdtYear.text.toString()
            val noteType=binding.idEdtType.text.toString()
            val noteCountry =binding.idEdtCountry.text.toString()
            val noteGender =binding.idEdtGender.text.toString()
            val noteLink =binding.idEdtNoteLink.text.toString()
            // on below line we are checking the type
            // and then saving or updating the data.

                if (noteTitle.isNotEmpty() && noteYear.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())


                    val database = Firebase.database
                    //val myRef = database.getReference("examinar")
                    val myRef = database.reference
                    val alphabet = 'a'..'z'


                    myRef.child("movie").get().addOnSuccessListener { response ->

                        val jsonArray = JSONArray(response.value.toString())
                        val idNumber = jsonArray.length()
                        myRef.child("movie").child("$idNumber").setValue(Movie("$noteTitle","$noteYear","$noteType","$noteCountry","$noteGender","$noteLink"))


                    }


                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }

            // opening the new activity on below line
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }*/