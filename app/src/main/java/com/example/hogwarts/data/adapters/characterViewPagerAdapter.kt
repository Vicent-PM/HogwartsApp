package com.example.hogwarts.data.adapters

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hogwarts.ui.fragments.CharacterFragment
import com.example.hogwarts.ui.fragments.characters.AllCharactersFragment
import com.example.hogwarts.ui.fragments.characters.StaffCharactersFragment
import com.example.hogwarts.ui.fragments.characters.StudentsCharacterFragment

class characterViewPagerAdapter(activity: CharacterFragment) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return if(position == 0) AllCharactersFragment() else if(position == 1) StaffCharactersFragment() else StudentsCharacterFragment()
    }
}