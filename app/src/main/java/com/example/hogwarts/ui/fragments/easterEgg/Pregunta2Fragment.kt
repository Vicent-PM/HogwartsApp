package com.example.hogwarts.ui.fragments.easterEgg

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.hogwarts.R
import com.example.hogwarts.databinding.PreguntaBinding

class Pregunta2Fragment : Fragment() {

    private var _binding: PreguntaBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PreguntaBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()


        (requireActivity() as AppCompatActivity).supportActionBar?.setTitle("Pregunta 2")

        binding.textViewPregunta.text = "¿Cuál es tu animal mágico favorito?"
        binding.radioButtonRespuesta1.text = "Dragón"
        binding.radioButtonRespuesta2.text = "Hipogrifo"
        binding.radioButtonRespuesta3.text = "Fénix"
        binding.radioButtonRespuesta4.text = "Elfo doméstico"

        binding.btnSiguiente.isEnabled = false

        binding.radioGroupRespuestas.setOnCheckedChangeListener { group, checkedId ->
            binding.btnSiguiente.isEnabled = true
        }

        binding.btnSiguiente.setOnClickListener {
            val selectedAnswer = binding.radioGroupRespuestas.checkedRadioButtonId
            val answer = when (selectedAnswer) {
                R.id.radioButtonRespuesta1 -> "Dragón"
                R.id.radioButtonRespuesta2 -> "Hipogrifo"
                R.id.radioButtonRespuesta3 -> "Fénix"
                R.id.radioButtonRespuesta4 -> "Elfo doméstico"
                else -> ""
            }

            val sharedPreferences = requireContext().getSharedPreferences("HogwartsPrefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().putString("pregunta2", answer).apply()

            navController.navigate(R.id.action_pregunta2Fragment_to_pregunta3Fragment)

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}