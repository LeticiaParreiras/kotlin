package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editGasolina = findViewById<EditText>(R.id.gasolina)
        val editEtanol = findViewById<EditText>(R.id.etanol)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val resultado = findViewById<TextView>(R.id.res)


        btnCalcular.setOnClickListener {
            val precoGasolina = editGasolina.text.toString()
            val precoEtanol = editEtanol.text.toString()

            if (precoGasolina.isNotEmpty() && precoEtanol.isNotEmpty()) {
                if (precoGasolina.toDouble() > 0.0 || precoEtanol.toDouble() > 0.0){
                    resultado.text = calcularMelhorOpcao(precoGasolina.toDouble(), precoEtanol.toDouble())
                }else{
                    resultado.text = "Os valores devem ser positivos"
                }

            } else {
                resultado.text = "Preencha os campos de preço"
            }
        }
    }

    private fun calcularMelhorOpcao(gas: Double, ethanol: Double): String {
        // Cálculo básico: se etanol / gasolina < 0.7, etanol vale a pena
        val resultado = ethanol / gas
     if (resultado <= 0.7) {
           return "Abasteça com Etanol"
        } else {
            return "Abasteça com Gasolina"
        }
    }
}