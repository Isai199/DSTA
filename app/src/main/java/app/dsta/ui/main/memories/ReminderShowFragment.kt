package app.dsta.ui.main.memories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import app.dsta.MemoriesFragmentDirections
import app.dsta.databinding.FragmentReminderShowBinding


class ReminderShowFragment : Fragment() {


    private var _binding: FragmentReminderShowBinding? = null



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    // get the arguments from the Registration fragment
    private val args : ReminderShowFragmentArgs by navArgs()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReminderShowBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // Receive the arguments in a variable
        val noteDetails = args.note


        binding.tvReminderShowTitle.setText("Titulo: "+noteDetails.note_title)
        binding.tvReminderShowDuedate.setText("Caduca: "+noteDetails.note_duedate)
        binding.tvReminderShowStardate.setText("Fecha inicio: "+ noteDetails.note_startdate)
        binding.tvReminderShowState.setText("Estado: "+ noteDetails.note_state)
        binding.tvReminderShowDescription .setText("Descripcion: "+noteDetails.note_description)




        binding.idBtnReminderShowExit.setOnClickListener {
            var directions = ReminderShowFragmentDirections.actionReminderShowFragmentToMemoriesFragment()
            NavHostFragment.findNavController(this).navigate(directions)
        }






        return  root
    }

}