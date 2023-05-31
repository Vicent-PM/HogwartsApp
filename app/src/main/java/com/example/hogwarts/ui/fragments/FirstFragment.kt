package com.example.hogwarts.ui.fragments

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hogwarts.R
import com.example.hogwarts.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var contador = 0
    private lateinit var sonido: MediaPlayer
    private var _binding: FragmentFirstBinding? = null
    private var soundPlayed = false



    // This property is only valid between onCreateView and
    // onDestroyView.
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
            }
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun reproSonido() {
        sonido = MediaPlayer.create(requireContext(), R.raw.flipendoo)
        sonido.start()
    }
}