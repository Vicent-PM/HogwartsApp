package com.example.hogwarts.ui.fragments.easterEgg

import Pregunta1Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.hogwarts.R

class SecretoFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bienvenida, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        (requireActivity() as AppCompatActivity).supportActionBar?.setTitle("")

        navController = Navigation.findNavController(view)

        val btnEmpezar = view.findViewById<Button>(R.id.btnEmpezar)
        btnEmpezar.setOnClickListener {
            navController.navigate(R.id.pregunta1Fragment)
        }
    }
}

