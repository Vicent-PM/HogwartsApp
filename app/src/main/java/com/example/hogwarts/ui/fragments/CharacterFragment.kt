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
import com.example.hogwarts.data.models.getCharacters.Characters
import com.example.hogwarts.databinding.FragmentCharacterBinding
import com.example.hogwarts.ui.MyViewModel
import com.google.android.material.tabs.TabLayoutMediator

class CharacterFragment: Fragment() {
    private var _binding: FragmentCharacterBinding? = null

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

        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val adapter = characterViewPagerAdapter(this@CharacterFragment)
            viewpager.adapter = adapter

            TabLayoutMediator(tabLayout2, viewpager) { tab, position ->
                tab.text = if(position == 0) "All" else if (position == 1) "Staff" else "Students"
                tab.setIcon(if(position == 0) R.drawable.harry_potter else if (position == 1) R.drawable.home else R.drawable.perno_de_luz)
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}