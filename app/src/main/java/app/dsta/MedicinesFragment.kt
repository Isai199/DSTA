package app.dsta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import app.dsta.databinding.FragmentMedicinesBinding
import app.dsta.ui.main.home.bar.ViewPagerAdapterMedicines
import com.google.android.material.tabs.TabLayout


class MedicinesFragment : Fragment() {

    private var _binding: FragmentMedicinesBinding? = null

    private lateinit var pager: ViewPager // creating object of ViewPager
    private lateinit var tab: TabLayout  // creating object of TabLayout


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMedicinesBinding.inflate(inflater, container, false)
        val root: View = binding.root




        //codigo para mostrar las tabbed
        tab =binding.tabs
        pager =binding.viewPager
        tab.addTab(tab.newTab().setText("Mañana"))
        tab.addTab(tab.newTab().setText("Tarde"))
        tab.addTab(tab.newTab().setText("Noche"))
        tab.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = ViewPagerAdapterMedicines(binding.root.context, childFragmentManager,
            tab.tabCount)
        pager.adapter = adapter
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab))
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        //termina aqui tabbed










        // Inflate the layout for this fragment
        return root
    }


}

