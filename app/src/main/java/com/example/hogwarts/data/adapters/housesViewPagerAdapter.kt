package com.example.hogwarts.data.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hogwarts.ui.fragments.HousesFragment
import com.example.hogwarts.ui.fragments.houses.GryffindorFragment
import com.example.hogwarts.ui.fragments.houses.HufflepuffFragment
import com.example.hogwarts.ui.fragments.houses.RavenclawFragment
import com.example.hogwarts.ui.fragments.houses.SlytherinFragment

class housesViewPagerAdapter(activity: HousesFragment) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return if(position == 0) GryffindorFragment() else if(position == 1) SlytherinFragment() else if(position == 2) RavenclawFragment() else HufflepuffFragment()
    }
}