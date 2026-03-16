package model

class CalculadoraModel {
    fun calcularMelhorOpcaoCombustivel( alcool:Double, gasolina : Double):CombustivelResult{
        val result = alcool/gasolina
        val melhorOpcao:String = if(result<=0.70){
            "Coloque Alcool"
        }else{
            "Coloque Gasolina"
        }
        return CombustivelResult(melhorOpcao, result)
    }
}
data class  CombustivelResult(
    val melhorOpcao : String,
    val valorDivisao: Double,
)