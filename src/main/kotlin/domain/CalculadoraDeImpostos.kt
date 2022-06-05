package domain

import domain.valueobjects.ResultadoDeImpostos

interface CalculadoraDeImpostos {
    fun calcular(baseDeCalculo: Int): ResultadoDeImpostos
}
