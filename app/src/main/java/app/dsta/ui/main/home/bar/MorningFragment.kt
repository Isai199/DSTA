package app.dsta.ui.main.home.bar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.dsta.SpecialistsActivity
import app.dsta.databinding.FragmentMorningBinding

class MorningFragment: Fragment() {
    private var _binding: FragmentMorningBinding? = null
    // inflate the layout

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {

        _binding = FragmentMorningBinding.inflate(inflater, container, false)
    val root: View = binding.root


        binding.idFABAddMorning.setOnClickListener{
            val intent = Intent(getActivity(), SpecialistsActivity::class.java)
            startActivity(intent)

        }




        // Inflate the layout for this fragment
        return root

    }

}