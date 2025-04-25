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

class ImcFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_imc, container, false)

        view.findViewById<Button>(R.id.btnCalcularImc).setOnClickListener {
            calcularImc(view)
        }

        return view
    }

    private fun calcularImc(view: View) {
        val pesoStr = view.findViewById<EditText>(R.id.etPeso).text.toString()
        val alturaStr = view.findViewById<EditText>(R.id.etAltura).text.toString()

        if (pesoStr.isNotEmpty() && alturaStr.isNotEmpty()) {
            val peso = pesoStr.toDouble()
            val altura = alturaStr.toDouble()

            if (altura > 0) {
                val imc = peso / (altura * altura)
                val resultado = "Tu IMC es: %.2f".format(imc)
                view.findViewById<TextView>(R.id.tvResultadoImc).text = resultado

                // Clasificación
                val clasificacion = when {
                    imc < 18.5 -> "Bajo peso"
                    imc < 25 -> "Peso normal"
                    imc < 30 -> "Sobrepeso"
                    else -> "Obesidad"
                }
                view.findViewById<TextView>(R.id.tvClasificacion).text = "Clasificación: $clasificacion"
            } else {
                Toast.makeText(requireContext(), "La altura debe ser mayor que cero", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Ingresa peso y altura", Toast.LENGTH_SHORT).show()
        }
    }
}