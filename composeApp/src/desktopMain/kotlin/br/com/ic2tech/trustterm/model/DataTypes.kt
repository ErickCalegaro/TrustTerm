package br.com.ic2tech.trustterm.model

data class Certificacao(
    val id: String,
    val nome: String,
    val solucao: String,
    val versaoSW: String,
    val ciclo: String,
    val espec: String,
    val usuario: String,
    val entidade: String,
    val statusExecucao: String,
    var roteiros: MutableList<Roteiro> = mutableListOf(),
    var tabelas: MutableList<Tabela> = mutableListOf()
)

data class Roteiro(
    val id: String,
    val nome: String,
    val versao: String,
    var grupos: MutableList<Grupo> = mutableListOf()
)

data class Tabela(
    val id: String,
    val nome: String,
    val acqIdx: String,
    val tabVer: String,
    val versao: String,
    var registros: MutableList<Registro> = mutableListOf()
)

data class Grupo(
    val id: String,
    val nome: String,
    val tabelaNome: String,
    val tabelaVersao: String,
    val testCases: MutableList<TestCase> = mutableListOf()
)

data class TestCase(
    val id: String,
    val nome: String,
    val descricao: String,
    val versao: String,
    val script: String,
    val fluxos: MutableList<Fluxo> = mutableListOf(),
    val comandos: MutableList<Comando> = mutableListOf()

)

data class Fluxo(
    val id: String,
    val itemId: String,
    var procedimento: Procedimento,
    var display: Display,
    var observacao: Observacao
)

data class Comando(
    val id: String,
    val itemId: String,
    val nome: String,
    val parametros: MutableList<Parametro> = mutableListOf()
)

data class Parametro(
    val id: String,
    val tipo: String,
    val nome: String,
    var valor: String
)

data class Procedimento(
    val valor: String
)

data class Display(
    val valor: String
)

data class Observacao(
    val valor: String
)

data class Registro(
    val codigo: String,
    var valor: String
)