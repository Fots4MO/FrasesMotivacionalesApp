package com.example.frasesmotivacionalesapp

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.frasesmotivacionalesapp.databinding.FragmentInicioBinding

class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val PREFS_NAME = "prefs"
        const val KEY_COLOR = "color_fondo"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ⬇️ Aplica el color de fondo al layout raíz
        val prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val color = prefs.getInt(KEY_COLOR, Color.WHITE) // Blanco por defecto
        binding.root.setBackgroundColor(color)

        // Navegar a detalles
        binding.btnVerFrase.setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_detallesFragment)
        }

        // Navegar a configuración
        binding.btnConfiguracion.setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_configuracionFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
