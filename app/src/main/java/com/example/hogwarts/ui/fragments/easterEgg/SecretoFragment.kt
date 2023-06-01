package com.example.hogwarts.ui.fragments.easterEgg

import Pregunta1Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.hogwarts.R

class SecretoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bienvenida, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnEmpezar = view.findViewById<Button>(R.id.btnEmpezar)
        btnEmpezar.setOnClickListener {
            val pregunta1Fragment = Pregunta1Fragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, pregunta1Fragment)
                .addToBackStack(null)
                .commit()
        }
    }

}