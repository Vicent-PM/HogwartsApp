package com.example.hogwarts.ui.fragments

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hogwarts.R
import com.example.hogwarts.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var contador = 0
    private lateinit var sonido: MediaPlayer
    private var _binding: FragmentFirstBinding? = null
    private var soundPlayed = false


    private val binding get() = _binding!!

    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.textView.setOnClickListener {
            contador++
            if (contador >= 10 && !soundPlayed) {
                reproSonido()
                soundPlayed = true
                mostrarToastConMensajePredefinido()

                val navController = findNavController()
                navController.navigate(R.id.action_FirstFragment_to_secretoFragment)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun reproSonido() {
        sonido = MediaPlayer.create(requireContext(), R.raw.flipendoo)
        sonido.start()
    }

    private fun mostrarToastConMensajePredefinido() {
        val mensaje = "Enhorabuena!! Has descubierto nuestro secreto"
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
    }
}