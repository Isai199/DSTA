package app.dsta.reminder

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import app.dsta.databinding.ActivityAddreminderBinding
import app.dsta.models.notes.MemoriesViewModel
import app.dsta.models.notes.Note
import app.dsta.models.users.ProfileViewModel
import app.dsta.picker.DatePickerFragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class AddReminderActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddreminderBinding



    //de room
    lateinit var showRV: RecyclerView
    private val mainViewModel: MemoriesViewModel by viewModels()
    //room users
    private val mainViewModelUser: ProfileViewModel by viewModels()


    //de picker
    lateinit var etDate: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddreminderBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //para la fecha
        etDate = binding.idEdtReminderDuedate


        etDate.setOnClickListener {
            showDatePickerDialog()

        }

// initialising Firebase auth object
        val database = Firebase.database
        val myRef = database.reference
        


        binding.idBtnReminderSave.setOnClickListener {

            val noteTitle = binding.idEdtReminderTitle.text.toString()
            val noteDuedate = binding.idEdtReminderDuedate.text.toString()
            val noteSate = binding.idEdtReminderState.text.toString()
            val noteDescription = binding.idEdtReminderDescription.text.toString()


            if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {

                //podria usar esto para start date
                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                val currentDateAndTime: String = sdf.format(Date())
                // if the string is not empty we are calling a
                // add note method to add data to our room database.



                mainViewModel.getNotes()
                mainViewModel.savedNotes.observe(this,{notesList ->


                   // if(!notesList.isNullOrEmpty()){
                    //para generar un id automaticamente
                        var id = 0

                        for(note in notesList){
                            Log.d("thesearethenotes","TodoNotas: id ${note.note_id} , ${note.note_title} , usrid: ${note.note_iduser}")
                            id++
                        }

                        //room user
                        mainViewModelUser.getUsers()
                        mainViewModelUser.savedUsers.observe(this, { usersList ->

                            if(!usersList.isNullOrEmpty()){
                                // on below line we are updating our list.


                                for(user in usersList){


                                    Log.d("userwithoutnote",user.user_id)


                                    mainViewModel.saveNote(
                                        Note(
                                            "${id+1}",
                                            "${noteTitle}",
                                            "${noteDuedate}",
                                            "${currentDateAndTime}",
                                            "${noteSate}",
                                            "${noteDescription}",
                                            "${user.user_id}")
                                    )

                                    Toast.makeText(this, "note Added", Toast.LENGTH_LONG).show()

                                    //actualizar lista notas de cada user firebase

                                    var idUser = 0

                                    for(note in notesList){
                                        if(user.user_id == note.note_iduser)
                                                idUser++
                                    }

                                    myRef.child("user").child("${user.user_id}").child("notes").setValue("${idUser+1}")

                                }

                            }else{
                                Log.d("userwithoutnote","null or empty")
                            }

                        })//termina room user









                   /* }else{
//primera nota cuando el recyclerview esta vasio
                        mainViewModel.saveNote(
                            Note(
                                "${3}",
                                "${noteTitle}",
                                "${noteDuedate}",
                                "${currentDateAndTime}",
                                "${noteSate}",
                                "${noteDescription}",
                                "2")
                        )

                        Toast.makeText(this, "first note", Toast.LENGTH_LONG).show()
                        Log.d("thesearethenotes","first note")
                    }*/
                })


            }



            finish()
        }


    }

//funciones picker
    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")

    }
    private fun onDateSelected(day: Int, month: Int, year: Int) {
        etDate.setText("caduca el $day del $month del año $year")
    }



}