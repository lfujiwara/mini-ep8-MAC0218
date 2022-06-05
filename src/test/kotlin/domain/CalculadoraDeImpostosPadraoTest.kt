package domain

import domain.implementations.CalculadoraDeImpostosPadrao
import domain.valueobjects.ResultadoDeImpostos
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@Suppress("unused")
internal class CalculadoraDeImpostosPadraoTest {

    private lateinit var underTest: CalculadoraDeImpostos

    @BeforeEach
    fun setup() {
        underTest = CalculadoraDeImpostosPadrao()
    }

    fun `executarTeste`(valor: Int, inssEsperado: Int, irrfEsperado: Int) {
        val valor = valor
        val impostosEsperados = ResultadoDeImpostos(inssEsperado, irrfEsperado)
        val resultado = underTest.calcular(valor)

        assertEquals(impostosEsperados.inss, resultado.inss)
        assertEquals(impostosEsperados.irrf, resultado.irrf)
    }

    @Test
    fun `calcula corretamente os impostos para base calculo 1212`() {
        `executarTeste`(121200, 9090, 0)
    }

    @Test
    fun `calcula corretamente os impostos para base calculo 3000`() {
        `executarTeste`(300000, 26900, 6202)
    }

    @Test
    fun `calcula corretamente os impostos para base calculo 5000`() {
        `executarTeste`(500000, 53618, 36823)
    }

    @Test
    fun `calcula corretamente os impostos para base calculo 10000`() {
        `executarTeste`(1000000, 82839, 165283)
    }
}
