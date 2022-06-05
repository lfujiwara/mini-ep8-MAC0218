package domain.implementations

import domain.CalculadoraDeImpostos
import domain.valueobjects.ResultadoDeImpostos
import kotlin.math.min
import kotlin.math.round

class CalculadoraDeImpostosPadrao : CalculadoraDeImpostos {

    private companion object {
        private class FaixaIRRF (val limiteInferior: Int, val limiteSuperior: Int, val aliquotaNumerador: Int, val aliquotaDenominador: Int, val deducao: Int)
        private class FaixaINSS (val limiteInferior: Int, val limiteSuperior: Int, val aliquotaNumerador: Int, val aliquotaDenominador: Int)

        private val faixasINSS = listOf(
            FaixaINSS(0, 121200, 75, 1000),
            FaixaINSS(121201, 242735, 9, 100),
            FaixaINSS(242736, 364103, 12, 100),
            FaixaINSS(364104, 708722, 14, 100),
        )

        private val faixasIRRF = listOf(
            FaixaIRRF(0, 190398, 0, 1, 0),
            FaixaIRRF(190399, 282665, 75, 1000, 14280),
            FaixaIRRF(282666, 375105, 15, 100, 35480),
            FaixaIRRF(375106, 466468, 225, 1000, 63613),
            FaixaIRRF(466469, Int.MAX_VALUE, 275, 1000, 86936)
        )

        private fun calcularPercentual(valor: Int, percentualNumerador: Int, percentualDenominador: Int): Int {
            return round((valor * percentualNumerador).toDouble() / percentualDenominador).toInt()
        }

        private fun calculaInss(baseDeCalculo: Int): Int {
            var inss = 0
            var baseInss = baseDeCalculo
            var limiteFaixaAnterior = 0

            for (faixa in faixasINSS) {
                var salarioDeContribuicao = min(faixa.limiteSuperior - limiteFaixaAnterior, baseInss)
                baseInss -= salarioDeContribuicao
                inss += calcularPercentual(salarioDeContribuicao, faixa.aliquotaNumerador, faixa.aliquotaDenominador)
                limiteFaixaAnterior = faixa.limiteSuperior
            }

            return inss
        }

        private fun calculaIRRF(baseDeCalculo: Int): Int {
            for (faixa in faixasIRRF) {
                if (baseDeCalculo <= faixa.limiteSuperior)
                    return calcularPercentual(baseDeCalculo, faixa.aliquotaNumerador, faixa.aliquotaDenominador) - faixa.deducao
            }
            return 0
        }
    }

    override fun calcular(baseDeCalculo: Int): ResultadoDeImpostos {
        var inss = calculaInss(baseDeCalculo)
        var irrf = calculaIRRF(baseDeCalculo - inss)

        return ResultadoDeImpostos(inss, irrf)
    }
}