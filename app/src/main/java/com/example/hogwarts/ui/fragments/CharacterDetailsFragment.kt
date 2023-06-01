package com.example.hogwarts.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.hogwarts.R
import com.example.hogwarts.databinding.FragmentDetailsCharcactersBinding
import com.example.hogwarts.ui.MainActivity
import com.example.hogwarts.ui.MyViewModel

class CharacterDetailsFragment: Fragment() {
    private var _binding: FragmentDetailsCharcactersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val myViewModel by activityViewModels<MyViewModel> {
        MyViewModel.MyViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsCharcactersBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.selectedCharacter.observe(viewLifecycleOwner) {
            binding.tvName.text = it.name
            binding.tvAlternateNames.text = it.alternateNames?.joinToString(", ")
            if(it.hogwartsStudent == true) {
                (requireActivity() as MainActivity).supportActionBar?.title = "Student"
            } else if (it.hogwartsStaff == true) {
                (requireActivity() as MainActivity).supportActionBar?.title = "Staff"
            } else if(it.wizard == true) {
                (requireActivity() as MainActivity).supportActionBar?.title = "Wizard"
            } else {
                (requireActivity() as MainActivity).supportActionBar?.title = "Muggle"
            }
            Glide.with(this).load(it.image).into(binding.tvImagenPersonaje)
            binding.tvSpecies.text = it.species
            when(it.gender){
                "male" -> binding.ivGender.setImageResource(R.drawable.genero_masculino)
                "female" -> binding.ivGender.setImageResource(R.drawable.femenino)
            }
            binding.tvHouse.text = it.house
            binding.tvDateBirth.text = it.dateOfBirth
            binding.tvYearBirth.text = it.yearOfBirth.toString()
            binding.tvWizard.text = it.wizard.toString()
            binding.tvAncestry.text = it.ancestry
            binding.tvEyeColour.text = it.eyeColour
            binding.tvHairColour.text = it.hairColour
            binding.tvWand.text = it.wand?.core + ", " + it.wand?.wood + ", " + it.wand?.length.toString()
            binding.tvPatronous.text = it.patronus
            if(it.hogwartsStudent == true) {
                binding.ivStudents.setImageResource(R.drawable.baseline_check_24)
            } else {
                binding.ivStudents.setImageResource(R.drawable.baseline_clear_24)
            }
            if(it.hogwartsStaff == true) {
                binding.ivStaff.setImageResource(R.drawable.baseline_check_24)
            } else {
                binding.ivStaff.setImageResource(R.drawable.baseline_clear_24)
            }
            binding.tvActor.text = it.actor
            if(it.alive == true) {
                binding.ivAlive.setImageResource(R.drawable.baseline_check_24)
            } else {
                binding.ivAlive.setImageResource(R.drawable.baseline_clear_24)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}