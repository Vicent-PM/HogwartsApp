package com.example.hogwarts.data.adapters

import Pregunta1Fragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hogwarts.ui.fragments.easterEgg.Pregunta2Fragment
import com.example.hogwarts.ui.fragments.easterEgg.Pregunta3Fragment
import com.example.hogwarts.ui.fragments.easterEgg.Pregunta4Fragment
import com.example.hogwarts.ui.fragments.easterEgg.Pregunta5Fragment

class preguntasAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = listOf(
        Pregunta1Fragment(),
        Pregunta2Fragment(),
        Pregunta3Fragment(),
        Pregunta4Fragment(),
        Pregunta5Fragment()
    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position] as Fragment
    }
}

