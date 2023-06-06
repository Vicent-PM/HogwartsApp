package com.example.hogwarts.ui.fragments.easterEgg

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.hogwarts.R
import com.example.hogwarts.databinding.ResultadohouseBinding

class ResultadoFragment : Fragment() {

    private var _binding: ResultadohouseBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private var onResultadoListener: OnResultadoListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ResultadohouseBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnResultadoListener) {
            onResultadoListener = context
        } else {
            throw RuntimeException("$context must implement OnResultadoListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "RESULTADO FINAL"

        val imageViewCasa = view.findViewById<ImageView>(R.id.imageViewCasa)
        val textViewResultado = view.findViewById<TextView>(R.id.textViewResultado)
        val btnVolverInicio = view.findViewById<Button>(R.id.btnVolverInicio)

        val resultadoCasa = obtenerResultadoCasa()

        textViewResultado.text = resultadoCasa

        when (resultadoCasa) {
            "Gryffindor" -> {
                imageViewCasa.setImageResource(R.drawable.casa2)
                textViewResultado.text = "Enhorabuena, perteneces a GRYFFINDOR"
            }
            "Slytherin" -> {
                imageViewCasa.setImageResource(R.drawable.casa3)
                textViewResultado.text = "Enhorabuena, perteneces a SLYTHERIN"
            }
            "Hufflepuff" -> {
                imageViewCasa.setImageResource(R.drawable.casa1)
                textViewResultado.text = "Enhorabuena, perteneces a HUFFLEPUFF"
            }
            "Ravenclaw" -> {
                imageViewCasa.setImageResource(R.drawable.casa4)
                textViewResultado.text = "Enhorabuena, perteneces a RAVENCLAW"
            }
        }

        onResultadoListener?.onResultadoCasaSeleccionada(resultadoCasa)

        btnVolverInicio.setOnClickListener {
            navController.navigate(R.id.action_resultadoFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun obtenerResultadoCasa(): String {
        val casas = listOf("Gryffindor", "Slytherin", "Hufflepuff", "Ravenclaw")
        return casas.random()
    }

    interface OnResultadoListener {
        fun onResultadoCasaSeleccionada(resultadoCasa: String)
    }
}
