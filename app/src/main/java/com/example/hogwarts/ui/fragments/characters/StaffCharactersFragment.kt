package com.example.hogwarts.ui.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hogwarts.R
import com.example.hogwarts.data.adapters.charactersAdapter
import com.example.hogwarts.data.models.getCharacters.Characters
import com.example.hogwarts.databinding.FragmentFirstBinding
import com.example.hogwarts.databinding.FragmentSpellsBinding
import com.example.hogwarts.databinding.FragmentStaffcharactersBinding
import com.example.hogwarts.ui.MyViewModel

class StaffCharactersFragment: Fragment() {
    private var _binding: FragmentStaffcharactersBinding? = null
    private lateinit var adapter: charactersAdapter
    private val myViewModel by activityViewModels<MyViewModel> {
        MyViewModel.MyViewModelFactory(requireContext())
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentStaffcharactersBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listAdapter = charactersAdapter(object : charactersAdapter.OnItemClickListener{
            override fun onItemClick(character: Characters) {
                myViewModel.selectedCharacter.value = character
                findNavController().navigate(R.id.action_characterFragment_to_characterDetailsFragment)
            }
        })

        val recyclerView = binding.recyclerview
        adapter = listAdapter
        val layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        myViewModel.staffCharactersLiveData.observe(viewLifecycleOwner) {
            listAdapter.update(it)
        }

        myViewModel.getStaffCharacters()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}