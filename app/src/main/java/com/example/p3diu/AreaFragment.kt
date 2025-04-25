package com.example.p3diu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class AreaFragment : Fragment() {

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_area, container, false)

        val spinner = rootView.findViewById<Spinner>(R.id.spFiguras)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                mostrarCamposSegunFigura(pos)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        rootView.findViewById<Button>(R.id.btnCalcularArea).setOnClickListener {
            calcularArea(spinner.selectedItemPosition)
        }

        return rootView
    }

    private fun mostrarCamposSegunFigura(pos: Int) {
        rootView.findViewById<LinearLayout>(R.id.llCamposCirculo).visibility = View.GONE
        rootView.findViewById<LinearLayout>(R.id.llCamposRectangulo).visibility = View.GONE

        when (pos) {
            0 -> rootView.findViewById<LinearLayout>(R.id.llCamposCirculo).visibility = View.VISIBLE
            1 -> rootView.findViewById<LinearLayout>(R.id.llCamposRectangulo).visibility = View.VISIBLE
        }
    }

    private fun calcularArea(figuraSeleccionada: Int) {
        when (figuraSeleccionada) {
            0 -> calcularAreaCirculo()
            1 -> calcularAreaRectangulo()
        }
    }

    private fun calcularAreaCirculo() {
        val radioStr = rootView.findViewById<EditText>(R.id.etRadio).text.toString()
        if (radioStr.isNotEmpty()) {
            val radio = radioStr.toDouble()
            val area = Math.PI * radio * radio
            mostrarResultado("Área del círculo: %.2f".format(area))
        } else {
            Toast.makeText(requireContext(), "Ingresa el radio", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calcularAreaRectangulo() {
        val baseStr = rootView.findViewById<EditText>(R.id.etBase).text.toString()
        val alturaStr = rootView.findViewById<EditText>(R.id.etAlturaRect).text.toString()

        if (baseStr.isNotEmpty() && alturaStr.isNotEmpty()) {
            val base = baseStr.toDouble()
            val altura = alturaStr.toDouble()
            val area = base * altura
            mostrarResultado("Área del rectángulo: %.2f".format(area))
        } else {
            Toast.makeText(requireContext(), "Ingresa base y altura", Toast.LENGTH_SHORT).show()
        }
    }

    private fun mostrarResultado(resultado: String) {
        rootView.findViewById<TextView>(R.id.tvResultadoArea).text = resultado
    }
}