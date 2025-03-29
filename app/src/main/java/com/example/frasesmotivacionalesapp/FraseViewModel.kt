package com.example.frasesmotivacionalesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FraseViewModel : ViewModel() {

    private val frases = listOf(
        "Cree en ti mismo y todo será posible.",
        "Cada día es una nueva oportunidad.",
        "El éxito es la suma de pequeños esfuerzos diarios.",
        "Nunca es tarde para empezar.",
        "Hazlo con pasión o no lo hagas.",
        "La motivación te impulsa, el hábito te mantiene.",
        "Tu actitud determina tu dirección.",
        "Las grandes cosas toman tiempo.",
        "Rodéate de energía positiva.",
        "Lo que haces hoy puede mejorar todas tus mañanas."
    )

    private val _fraseActual = MutableLiveData<String>()
    val fraseActual: LiveData<String> get() = _fraseActual

    init {
        mostrarFraseAleatoria()
    }

    fun mostrarFraseAleatoria() {
        _fraseActual.value = frases.random()
    }
}
