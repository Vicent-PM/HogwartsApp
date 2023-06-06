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

class Pregunta5Fragment : Fragment() {

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


        (requireActivity() as AppCompatActivity).supportActionBar?.setTitle("Pregunta 5")

        binding.textViewPregunta.text = "¿Cuál de estos objetos mágicos te gustaría tener?"
        binding.radioButtonRespuesta1.text = "La Capa de Invisibilidad"
        binding.radioButtonRespuesta2.text = "La Varita de Saúco"
        binding.radioButtonRespuesta3.text = "La Piedra de la Resurrección"
        binding.radioButtonRespuesta4.text = "El Mapa del Merodeador"

        binding.btnSiguiente.isEnabled = false
        binding.radioGroupRespuestas.setOnCheckedChangeListener { group, checkedId ->
            binding.btnSiguiente.isEnabled = true
        }

        binding.btnSiguiente.setOnClickListener {
            val selectedAnswer = binding.radioGroupRespuestas.checkedRadioButtonId
            val answer = when (selectedAnswer) {
                R.id.radioButtonRespuesta1 -> "La Capa de Invisibilidad"
                R.id.radioButtonRespuesta2 -> "La Varita de Saúco"
                R.id.radioButtonRespuesta3 -> "La Piedra de la Resurrección"
                R.id.radioButtonRespuesta4 -> "El Mapa del Merodeador"
                else -> ""
            }

            val sharedPreferences = requireContext().getSharedPreferences("HogwartsPrefs", Context.MODE_PRIVATE)
            val pregunta1Answer = sharedPreferences.getString("pregunta1", null)
            val pregunta2Answer = sharedPreferences.getString("pregunta2", null)
            val pregunta3Answer = sharedPreferences.getString("pregunta3", null)
            val pregunta4Answer = sharedPreferences.getString("pregunta4", null)

            if (pregunta1Answer != null && pregunta2Answer != null && pregunta3Answer != null && pregunta4Answer != null) {
                navController.navigate(R.id.action_pregunta5Fragment_to_resultadoFragment)
            }

        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
