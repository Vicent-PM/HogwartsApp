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

class Pregunta1Fragment : Fragment() {

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

        (requireActivity() as AppCompatActivity).supportActionBar?.setTitle("Pregunta 1")

        binding.textViewPregunta.text = "¿Cuál es tu asignatura favorita?"
        binding.radioButtonRespuesta1.text = "Pociones"
        binding.radioButtonRespuesta2.text = "Transformaciones"
        binding.radioButtonRespuesta3.text = "Herbología"
        binding.radioButtonRespuesta4.text = "Encantamientos"

        binding.btnSiguiente.isEnabled = false
        binding.radioGroupRespuestas.setOnCheckedChangeListener { group, checkedId ->
            binding.btnSiguiente.isEnabled = true
        }

        binding.btnSiguiente.setOnClickListener {
            val selectedAnswer = binding.radioGroupRespuestas.checkedRadioButtonId
            val answer = when (selectedAnswer) {
                R.id.radioButtonRespuesta1 -> "Pociones"
                R.id.radioButtonRespuesta2 -> "Transformaciones"
                R.id.radioButtonRespuesta3 -> "Herbología"
                R.id.radioButtonRespuesta4 -> "Encantamientos"
                else -> ""
            }

            val sharedPreferences = requireContext().getSharedPreferences("HogwartsPrefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().putString("pregunta1", answer).apply()

            navController.navigate(R.id.action_pregunta1Fragment_to_pregunta2Fragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


