package com.example.p3diu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class InteresFragment : Fragment() {

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_interes, container, false)

        rootView.findViewById<Button>(R.id.btnCalcularInteres).setOnClickListener {
            calcularInteresSimple()
        }

        return rootView
    }

    private fun calcularInteresSimple() {
        val capitalStr = rootView.findViewById<EditText>(R.id.etCapital).text.toString()
        val tasaStr = rootView.findViewById<EditText>(R.id.etTasa).text.toString()
        val tiempoStr = rootView.findViewById<EditText>(R.id.etTiempo).text.toString()

        if (capitalStr.isNotEmpty() && tasaStr.isNotEmpty() && tiempoStr.isNotEmpty()) {
            val capital = capitalStr.toDouble()
            val tasa = tasaStr.toDouble()
            val tiempo = tiempoStr.toDouble()

            val interes = capital * (tasa/100) * tiempo
            val total = capital + interes

            val resultado = """
                Interés generado: %.2f
                Monto total: %.2f
            """.trimIndent().format(interes, total)

            rootView.findViewById<TextView>(R.id.tvResultadoInteres).text = resultado
        } else {
            Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}