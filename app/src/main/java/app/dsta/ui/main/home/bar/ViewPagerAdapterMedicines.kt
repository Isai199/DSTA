package app.dsta.ui.main.home.bar

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

internal class ViewPagerAdapterMedicines(var context: Context,
                                         fm: FragmentManager,
                                         var totalTabs: Int) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MorningFragment()
            }
            1 -> {
                AfternoonFragment()
            }
            2 -> {
                NightFragment()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}