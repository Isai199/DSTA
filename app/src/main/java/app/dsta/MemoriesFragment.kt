package app.dsta

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorDestinationBuilder
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import app.dsta.adapters.memories.MemoriesAdapter
import app.dsta.databinding.FragmentMemoriesBinding
import app.dsta.models.notes.MemoriesViewModel
import app.dsta.models.notes.Note
import app.dsta.models.users.ProfileViewModel
import app.dsta.reminder.AddReminderActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MemoriesFragment : Fragment(), MemoriesAdapter.NoteClickDeleteInterface , MemoriesAdapter.NoteClickShowInterface{

    private var _binding: FragmentMemoriesBinding? = null



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    //de room
    lateinit var showRV: RecyclerView
    private val mainViewModel: MemoriesViewModel by viewModels()
    //room users
    private val mainViewModelUser: ProfileViewModel by viewModels()


    //lateinit var viewModal:



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMemoriesBinding.inflate(inflater, container, false)
        val root: View = binding.root


       //para lo del adapter

        showRV = binding.rvReminderPrincipal
        // on below line we are initializing our adapter class.
        val showRVAdapter = MemoriesAdapter(this, this, this)
        // on below line we are setting
        // adapter to our recycler view.
        showRV.adapter = showRVAdapter







        binding.idFABReminderAddReminder.setOnClickListener {
            val intent = Intent(getActivity(), AddReminderActivity::class.java)
            startActivity(intent)

        }






        mainViewModel.getNotes()

        //si es fragment
        //es con viewLifecycleOwner en lugar de this
        //room note
       mainViewModel.savedNotes.observe(viewLifecycleOwner,{notesList ->
           //para refrescar por que sale mas items de los solicitados(solucion temporal)
           val refresh = mutableListOf<Note>()
           showRVAdapter.updateList(refresh)


            if(!notesList.isNullOrEmpty()){

                // on below line we are updating our list.
                //showRVAdapter.updateList(notesList)
                val entrees = mutableListOf<Note>()

                for(note in notesList){



                    //room user
                    mainViewModelUser.getUsers()
                    mainViewModelUser.savedUsers.observe(viewLifecycleOwner, { usersList ->

                        if(!usersList.isNullOrEmpty()){
                            // on below line we are updating our list.


                            for(user in usersList){

                                if(note.note_iduser == user.user_id){
                                    Log.d("thesearethenotes",note.note_title)

                                    entrees += note



                                }
                                Log.d("userwithoutnote",user.user_id)

                            }


                        }else{
                            Log.d("userwithoutnote","null or empty")
                        }

                    })



                }




                showRVAdapter.updateList(entrees)
            }else{
                Log.d("thesearethenotes","null or empty")
            }
        })








       /* binding.idFAB.setOnClickListener {
            val intent = Intent(getActivity(), AddReminderActivity::class.java)
            startActivity(intent)
        }*/




        return root
    }


    override fun onDeleteIconClick(note: Note) {
        // in on note click method we are calling delete
        // method from our view modal to delete our not.

        mainViewModel.deleteNote(note)
        // displaying a toast message
        Toast.makeText(getActivity(), "${note.note_title} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onShowIconClick(note: Note) {



        var directions = MemoriesFragmentDirections.actionMemoriesFragmentToReminderShowFragment(note)
        NavHostFragment.findNavController(this).navigate(directions)

        Toast.makeText(getActivity(), "mostrar esta nota", Toast.LENGTH_LONG).show()
    }


}

/*
        mainViewModel.getUsers()

        var fakeVideoData: Array<JSONObject>

        fakeVideoData = arrayOf(
            JSONObject("{\"user\": \"hola\"}"),
        )
        //si es fragment
        //es con viewLifecycleOwner en lugar de this
        mainViewModel.savedUsers.observe(this,{usersList ->


            if(!usersList.isNullOrEmpty()){
                // on below line we are updating our list.
                showRVAdapter.updateList(usersList)
                for(user in usersList){
                    Log.d("thesearetheusers",user.user_name)



                }
            }else{
                Log.d("thesearetheusers","null or empty")
            }
        })*/




/*lateinit var viewModal: NoteViewModal
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // on below line we are initializing
        // all our variables.
        notesRV = binding.notesRV
        addFAB = binding.idFAB

        // on below line we are setting layout
        // manager to our recycler view.
        notesRV.layoutManager = LinearLayoutManager(this)

        // on below line we are initializing our adapter class.
        val noteRVAdapter = NoteRVAdapter(this, this, this)

        // on below line we are setting
        // adapter to our recycler view.
        notesRV.adapter = noteRVAdapter

        // on below line we are
        // initializing our view modal.
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)*/