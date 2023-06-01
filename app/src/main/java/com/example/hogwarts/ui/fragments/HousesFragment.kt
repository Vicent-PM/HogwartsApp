package com.example.hogwarts.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hogwarts.R
import com.example.hogwarts.data.adapters.characterViewPagerAdapter
import com.example.hogwarts.data.adapters.charactersAdapter
import com.example.hogwarts.data.adapters.housesViewPagerAdapter
import com.example.hogwarts.data.models.getCharacters.Characters
import com.example.hogwarts.databinding.FragmentFirstBinding
import com.example.hogwarts.databinding.FragmentHousesBinding
import com.example.hogwarts.ui.MyViewModel
import com.google.android.material.tabs.TabLayoutMediator

class HousesFragment: Fragment() {
    private var _binding: FragmentHousesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: charactersAdapter
    private val myViewModel by activityViewModels<MyViewModel> {
        MyViewModel.MyViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHousesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val adapter = housesViewPagerAdapter(this@HousesFragment)
            viewpager.adapter = adapter

            TabLayoutMediator(tablayout, viewpager) { tab, position ->
                tab.text = if(position == 0) "Gryffindor" else if (position == 1) "Slytherin" else if (position == 2) "Ravenclaw" else "Hufflepuff"
                tab.setIcon(if(position == 0) R.drawable.gryffindor_icono else if (position == 1) R.drawable.slytherin_icono else if (position == 2) R.drawable.ravenclaw_icono else R.drawable.hufflepuff_icono)

            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}