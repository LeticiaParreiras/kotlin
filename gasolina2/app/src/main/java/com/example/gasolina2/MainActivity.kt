package com.example.gasolina2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.gasolina2.databinding.ActivityMainBinding
import viewmodel.CalculadoraViewModel // Ensure this matches your actual package structure

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CalculadoraViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurarListeners()
        configurarObservar()


    }
    fun configurarObservar(){
        viewModel.resultCalculo.observe(this, Observer {
            // se não for nullo entra la ???
            result -> result?.let{
                binding.textViewResult.text = it.melhorOpcao
        }
        })
    }

    fun configurarListeners(){
        binding.btnCalcular.setOnClickListener{
        val alcool = binding.editTextAlcool.text.toString()
        val gasolina = binding.editTextGasolina.text.toString()
        viewModel.calculo(alcool,gasolina)

        }

    }

}