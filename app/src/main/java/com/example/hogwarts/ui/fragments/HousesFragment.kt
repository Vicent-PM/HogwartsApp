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

        val listAdapter = charactersAdapter(object : charactersAdapter.OnItemClickListener{
            override fun onItemClick(character: Characters) {
                myViewModel.selectedCharacter.value = character
                findNavController().navigate(R.id.action_housesFragment_to_characterDetailsFragment)
            }
        })

        TabLayoutMediator(tabLayout, recyclerView) { tab, position ->
            tab.text = when (position) {
                0 -> "Gryffindor"
                1 -> "Hufflepuff"
                2 -> "Ravenclaw"
                3 -> "Slytherin" //
                else -> ""
            }
        }.attach()

        val recyclerView = binding.recyclerView
        adapter = listAdapter
        val layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        myViewModel.houseCharactersLiveData.observe(viewLifecycleOwner) {
            listAdapter.update(it)
        }

        myViewModel.getCharacters()

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_buscar, menu)

                val menuItem = menu.findItem(R.id.app_bar_search)
                val searchView = menuItem.actionView as SearchView

                searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        adapter.filter.filter(p0)
                        return true
                    }

                    override fun onQueryTextChange(p0: String?): Boolean {
                        adapter.filter.filter(p0)
                        return true
                    }
                })
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_exit -> {
                        val builder = AlertDialog.Builder(requireContext())
                        builder.setTitle("Close App")
                        builder.setMessage("Are you sure you want to close the app?")
                        builder.setPositiveButton("Yes") { _, _ ->
                            requireActivity().finishAffinity()
                        }
                        builder.setNegativeButton("No", null)
                        val dialog = builder.create()
                        dialog.show()
                        return true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}