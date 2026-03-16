package com.example.imc.model

data class ImcResult(
    val indice: Double,
    val classificacao: String
)

class ImcModel {
    fun calculate(peso: Double, altura: Double): ImcResult {
        val indice = peso / (altura * altura)
        val classificacao = when {
            indice < 18.5 -> "Abaixo do peso"
            indice < 25 -> "Peso normal"
            indice < 30 -> "Sobrepeso"
            indice < 40 -> "Obesidade"
            else -> "Obesidade Grave"
        }
        return ImcResult(indice, classificacao)
    }
}