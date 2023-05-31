package com.example.hogwarts.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hogwarts.R
import com.example.hogwarts.data.adapters.spellsAdapter
import com.example.hogwarts.databinding.FragmentFirstBinding
import com.example.hogwarts.databinding.FragmentSpellsBinding
import com.example.hogwarts.ui.MyViewModel

class SpellsFragment: Fragment() {
    private var _binding: FragmentSpellsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: spellsAdapter
    private val myViewModel by activityViewModels<MyViewModel> {
        MyViewModel.MyViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSpellsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        adapter = spellsAdapter()
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        myViewModel.spellsLiveData.observe(viewLifecycleOwner) {
            adapter.update(it)
        }

        myViewModel.getSpells()

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