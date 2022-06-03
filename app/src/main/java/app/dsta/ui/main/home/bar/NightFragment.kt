package app.dsta.ui.main.home.bar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.dsta.databinding.FragmentNightBinding

class NightFragment : Fragment(){
    private var _binding: FragmentNightBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {

        _binding = FragmentNightBinding.inflate(inflater, container, false)
        val root: View = binding.root




        // Inflate the layout for this fragment
        return root

    }
}