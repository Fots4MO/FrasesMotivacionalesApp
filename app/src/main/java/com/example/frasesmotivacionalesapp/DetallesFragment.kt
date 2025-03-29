package com.example.frasesmotivacionalesapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment

// Para font
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

// Para fondos
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale


class DetallesFragment : Fragment() {

    private val viewModel: FraseViewModel by viewModels()

    companion object {
        const val PREFS_NAME = "prefs"
        const val KEY_COLOR = "color_fondo"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val color = obtenerColorFondo()

        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    FraseMotivacionalUI(
                        viewModel = viewModel,
                        backgroundColor = ComposeColor(color)
                    )
                }
            }
        }
    }

    private fun obtenerColorFondo(): Int {
        val prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(KEY_COLOR, android.graphics.Color.WHITE)
    }
}

@Composable
fun FraseMotivacionalUI(viewModel: FraseViewModel, backgroundColor: ComposeColor) {
    // Lista de fondos de pantalla
    val fondos = listOf(
        R.drawable.fondo_cartoon1,
        R.drawable.fondo_cartoon2,
        R.drawable.fondo_cartoon3
    )

    // Selección aleatoria del fondo
    val fondoSeleccionado = remember { fondos.random() }

    val frase by viewModel.fraseActual.observeAsState("Cargando...")
    val cartoonFont = FontFamily(Font(R.font.fredoka))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = frase,
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = cartoonFont,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.mostrarFraseAleatoria() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Otra frase")
        }

        // Espacio antes de la imagen decorativa
        Spacer(modifier = Modifier.height(16.dp))

        // Imagen pequeña como adorno debajo del botón
        Image(
            painter = painterResource(id = fondoSeleccionado),
            contentDescription = "Fondo cartoon decorativo",
            modifier = Modifier
                .size(100.dp)  // Tamaño pequeño para la imagen
                .align(Alignment.CenterHorizontally) // Centrada debajo del botón
        )
    }
}
