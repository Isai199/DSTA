package app.dsta

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import app.dsta.adapters.RecentlyGamesAdapter
import app.dsta.adapters.ReminderAdapter
import app.dsta.databinding.FragmentHomeBinding
import app.dsta.maps.MapsActivity
import org.json.JSONObject


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.cvMaps.setOnClickListener{

            val intent = Intent(binding.root.context, MapsActivity::class.java)
            startActivity(intent)
        }


        //adapter reminder
        val fakeVideoData: Array<JSONObject> = arrayOf(
            JSONObject("{\"title\": \"Ir al super\", \"textouno\": \"Comprar manzanas\", \"textodos\": \"walmart\"}"),
            JSONObject("{\"title\": \"Comprar medicinas\", \"textouno\": \"Comprar calmantes\", \"textodos\": \"farmacia\"}"),
            JSONObject("{\"title\": \"Salir a caminar\", \"textouno\": \"Caminar 30 min\", \"textodos\": \"parque\"}"),
        )

        binding.rvReminder.adapter = ReminderAdapter(fakeVideoData)

        binding.btnVerRecordatorios.setOnClickListener {
                view: View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_medicinesFragment)
        }

        //adapter games recently

        val fakeVideoDataDos: Array<JSONObject> = arrayOf(
            JSONObject("{\"title\": \"Memorama\", \"textouno\": \"Nivel 10\", \"textodos\": \"Jugado hoy por 2 horas\"}"),
            JSONObject("{\"title\": \"Sudoku\", \"textouno\": \"4 sudokus completados\", \"textodos\": \"Jugado hace 2 dias\"}"),
        )

        binding.rvGemesRecently.adapter = RecentlyGamesAdapter(fakeVideoDataDos)

        binding.btnVerProgreso.setOnClickListener {

        }




        // Inflate the layout for this fragment
        return root

    }

}

