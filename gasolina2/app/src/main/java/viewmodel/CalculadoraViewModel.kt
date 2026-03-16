package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.CalculadoraModel
import model.CombustivelResult

class CalculadoraViewModel:ViewModel() {
    private val calcModel = CalculadoraModel()
    private val _resultCalculo = MutableLiveData<CombustivelResult>()
    val resultCalculo = _resultCalculo

    fun calculo (alcool : String, gasolina: String){
        _resultCalculo.value = null
        val result = calcModel.calcularMelhorOpcaoCombustivel(alcool.toDouble(), gasolina.toDouble())
        _resultCalculo.value = result

    }
}