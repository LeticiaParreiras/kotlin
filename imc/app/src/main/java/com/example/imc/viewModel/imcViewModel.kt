package com.example.imc.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imc.model.ImcModel
import com.example.imc.model.ImcResult

class ImcViewModel : ViewModel() {
    private val calculadoraImc = ImcModel()

    private val _imcResult = MutableLiveData<ImcResult>()
    val imcResult: LiveData<ImcResult> = _imcResult

    fun calcularIMC(peso: String, altura: String) {
        if (peso.isNotEmpty() && altura.isNotEmpty()) {
            try {
                val resultado = calculadoraImc.calculate(peso.toDouble(), altura.toDouble())
                _imcResult.value = resultado
            } catch (e: NumberFormatException) {
                // Tratar erro de formato se necessário
            }
        }
    }
}