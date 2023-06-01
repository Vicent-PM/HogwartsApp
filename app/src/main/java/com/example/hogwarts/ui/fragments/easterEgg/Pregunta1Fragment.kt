import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hogwarts.R
import com.example.hogwarts.databinding.PreguntaBinding

class Pregunta1Fragment : Fragment() {

    private var _binding: PreguntaBinding? = null
    private val binding get() = _binding!!

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

        binding.textViewPregunta.text = "¿Cuál es tu asignatura favorita?"
        binding.radioButtonRespuesta1.text = "Pociones"
        binding.radioButtonRespuesta2.text = "Transformaciones"
        binding.radioButtonRespuesta3.text = "Herbología"
        binding.radioButtonRespuesta4.text = "Encantamientos"

        binding.btnSiguiente.setOnClickListener {
            val selectedAnswer = binding.radioGroupRespuestas.checkedRadioButtonId
            val answer = when (selectedAnswer) {
                R.id.radioButtonRespuesta1 -> "Pociones"
                R.id.radioButtonRespuesta2 -> "Transformaciones"
                R.id.radioButtonRespuesta3 -> "Herbología"
                R.id.radioButtonRespuesta4 -> "Encantamientos"
                else -> ""
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

