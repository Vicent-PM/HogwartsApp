package com.example.hogwarts.data.adapters

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hogwarts.ui.fragments.CharacterFragment
import com.example.hogwarts.ui.fragments.characters.AllCharactersFragment
import com.example.hogwarts.ui.fragments.characters.StaffCharactersFragment
import com.example.hogwarts.ui.fragments.characters.StudentsCharacterFragment

class characterViewPagerAdapter(activity: CharacterFragment) : FragmentStateAdapter(activity) {

//    private val listaCasas = ArrayList<String>()
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {

//        val casa = listaCasas[position]
//        val bundle = bundleOf("casa" to casa)
//        val fragment = AllCharactersFragment()
//        fragment.arguments = bundle
        return if(position == 0) AllCharactersFragment() else if(position == 1) StaffCharactersFragment() else StudentsCharacterFragment()
    }
}