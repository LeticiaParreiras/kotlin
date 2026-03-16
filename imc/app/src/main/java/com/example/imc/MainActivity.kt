package com.example.imc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.imc.databinding.ActivityMainBinding
import com.example.imc.viewModel.ImcViewModel

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ImcViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        configurarListeners()
        configurarObservadores()
    }

    private fun configurarObservadores() {
        viewModel.imcResult.observe(this, Observer { result ->
            result?.let {
                binding.textViewResult.text = getString(R.string.resultado_imc, it.indice, it.classificacao)
            }
        })
    }

    private fun configurarListeners() {
        binding.btnCalcular.setOnClickListener {
            val altura = binding.editTextAltura.text.toString()
            val peso = binding.editTextPeso.text.toString()
            viewModel.calcularIMC(peso, altura)
        }
    }
}
