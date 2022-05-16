package domain

import domain.criterios.Basico
import domain.criterios.CriterioDeAprovacao

class AnalisadorDeAprovacao {
    var criterio: CriterioDeAprovacao = Basico()
        private set

    fun defineCriterio(criterio: CriterioDeAprovacao) {
        this.criterio = criterio
    }

    // ---------------------------------
    //
    //      Seu código deve vir aqui
    //
    //      Defina atributos e métodos conforme especificado
    //      no arquivo de teste encontrado em
    //      'src/test/kotlin/domain/AnalisadorDeAprovacaoTest'
    //
    // ---------------------------------

    fun fechaBoletim(boletim: Boletim): BoletimFechado {
        return BoletimFechado(
            boletim.mediaEPs,
            boletim.mediaMiniEPs,
            criterio.mediaFinal(boletim),
            criterio.estaAprovado(boletim)
        )
    }
}