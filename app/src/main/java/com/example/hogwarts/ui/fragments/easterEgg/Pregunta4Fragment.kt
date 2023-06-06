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

class Pregunta4Fragment : Fragment() {

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


        (requireActivity() as AppCompatActivity).supportActionBar?.setTitle("Pregunta 4")

        binding.textViewPregunta.text = "¿Qué tipo de magia prefieres?"
        binding.radioButtonRespuesta1.text = "Magia negra"
        binding.radioButtonRespuesta2.text = "Magia poderosa"
        binding.radioButtonRespuesta3.text = "Magia curativa"
        binding.radioButtonRespuesta4.text = "Magia elemental"

        binding.btnSiguiente.isEnabled = false
        binding.radioGroupRespuestas.setOnCheckedChangeListener { group, checkedId ->
            binding.btnSiguiente.isEnabled = true
        }
        binding.btnSiguiente.setOnClickListener {
            val selectedAnswer = binding.radioGroupRespuestas.checkedRadioButtonId
            val answer = when (selectedAnswer) {
                R.id.radioButtonRespuesta1 -> "Magia negra"
                R.id.radioButtonRespuesta2 -> "Magia poderosa"
                R.id.radioButtonRespuesta3 -> "Magia curativa"
                R.id.radioButtonRespuesta4 -> "Magia elemental"
                else -> ""
            }
            val sharedPreferences = requireContext().getSharedPreferences("HogwartsPrefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().putString("pregunta4", answer).apply()

            navController.navigate(R.id.action_pregunta4Fragment_to_pregunta5Fragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
