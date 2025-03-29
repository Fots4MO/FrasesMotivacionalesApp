package com.example.frasesmotivacionalesapp

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.frasesmotivacionalesapp.databinding.FragmentConfiguracionBinding

class ConfiguracionFragment : Fragment() {

    private var _binding: FragmentConfiguracionBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val PREFS_NAME = "prefs"
        const val KEY_COLOR = "color_fondo"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfiguracionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnColorAzul.setOnClickListener {
            guardarColor(Color.parseColor("#BBDEFB")) // Azul claro
        }

        binding.btnColorVerde.setOnClickListener {
            guardarColor(Color.parseColor("#C8E6C9")) // Verde claro
        }

        binding.btnColorRosa.setOnClickListener {
            guardarColor(Color.parseColor("#F8BBD0")) // Rosa claro
        }
    }

    private fun guardarColor(color: Int) {
        val prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putInt(KEY_COLOR, color).apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
