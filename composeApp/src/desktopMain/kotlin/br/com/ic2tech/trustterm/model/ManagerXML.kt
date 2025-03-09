package br.com.ic2tech.trustterm.model
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.ByteArrayInputStream
import java.io.File
import javax.xml.parsers.SAXParserFactory
import kotlin.time.measureTime

class ManagerXML {
    companion object{
        var certificacao: Certificacao? = null
    }

    class XmlHandler : DefaultHandler() {
        private var currentElement = ""
        private var roteiro: Roteiro? = null
        private var tabela: Tabela? = null
        private var grupo: Grupo? = null
        private var testCase: TestCase? = null
        private var fluxo: Fluxo? = null
        private var procedimento = Procedimento(valor = "")
        private var display = Display(valor = "")
        private var observacao = Observacao(valor = "")
        private var registro = Registro(codigo = "", valor = "")
        private var comando: Comando? = null
        private var parametro: Parametro? = null
        private var valorAtual = ""

        override fun startElement(uri: String?, localName: String?, qName: String, attributes: Attributes) {
            currentElement = qName

            when (qName) {
                "certificacao" -> {
                    certificacao = Certificacao(
                        nome = attributes.getValue("nome") ?: "",
                        id = attributes.getValue("Id") ?: "",
                        solucao = attributes.getValue("solucao") ?: "",
                        versaoSW = attributes.getValue("versaoSW") ?: "",
                        ciclo = attributes.getValue("ciclo") ?: "",
                        espec = attributes.getValue("espec") ?: "",
                        usuario = attributes.getValue("usuario") ?: "",
                        entidade = attributes.getValue("entidade") ?: "",
                        statusExecucao = attributes.getValue("statusExecucao") ?: ""
                    )
                    certificacao?.roteiros = mutableListOf()
                    certificacao?.tabelas = mutableListOf()
                }

                "roteiro" -> {
                    roteiro = Roteiro(
                        id = attributes.getValue("Id") ?: "",
                        nome = attributes.getValue("nome") ?: "",
                        versao = attributes.getValue("versao") ?: ""
                    )
                }

                "tabela" -> {
                    tabela = Tabela(
                        id = attributes.getValue("Id") ?: "",
                        nome = attributes.getValue("nome") ?: "",
                        acqIdx = attributes.getValue("acqIdx") ?: "",
                        tabVer = attributes.getValue("tabVer") ?: "",
                        versao = attributes.getValue("versao") ?: ""
                    )
                }

                "grupo" -> {
                    grupo = Grupo(
                        id = attributes.getValue("Id") ?: "",
                        nome = attributes.getValue("nome") ?: "",
                        tabelaNome = attributes.getValue("tabelaNome") ?: "",
                        tabelaVersao = attributes.getValue("tabelaVersao") ?: ""
                    )
                }

                "testcase" -> {
                    testCase = TestCase(
                        id = attributes.getValue("Id") ?: "",
                        nome = attributes.getValue("nome") ?: "",
                        descricao = attributes.getValue("descricao") ?: "",
                        versao = attributes.getValue("versao") ?: "",
                        script = attributes.getValue("script") ?: ""
                    )
                }

                "fluxo" -> {
                    fluxo = Fluxo(
                        id = attributes.getValue("Id") ?: "",
                        itemId = attributes.getValue("itemId") ?: "",
                        procedimento = Procedimento(valor = ""),
                        display = Display(valor = ""),
                        observacao = Observacao(valor = "")
                    )
                }

                "comando" -> {
                    comando = Comando(
                        id = attributes.getValue("Id") ?: "",
                        itemId = attributes.getValue("itemId") ?: "",
                        nome = attributes.getValue("nome") ?: ""
                    )
                }

                "parametro" -> {
                    parametro = Parametro(
                        id = attributes.getValue("Id") ?: "",
                        nome = attributes.getValue("nome") ?: "",
                        tipo = attributes.getValue("tipo") ?: "",
                        valor = ""
                    )
                    valorAtual = ""
                }

                "procedimento" -> {
                    procedimento = Procedimento(
                        valor = ""
                    )
                    valorAtual = ""
                }

                "display" -> {
                    display = Display(
                        valor = ""
                    )
                    valorAtual = ""
                }

                "observacao" -> {
                    observacao = Observacao(
                        valor = ""
                    )
                    valorAtual = ""
                }

                "registro" -> {
                    registro = Registro(
                        codigo = attributes.getValue("codigo") ?: "",
                        valor = ""
                    )
                    valorAtual = ""
                }
            }
        }

        override fun characters(ch: CharArray, start: Int, length: Int) {
            valorAtual += String(ch, start, length).trim()
        }

        override fun endElement(uri: String?, localName: String?, qName: String) {
            when (qName) {

                "certificacao" -> {
//                    println("certificacao: ${certificacao}")
                }

                "roteiro" -> {
                    roteiro?.let {
                        certificacao?.roteiros?.add(
                            it
                        )
                    }
//                    println("certificacao?.roteiros: ${certificacao?.roteiros}")
                }

                "tabela" -> {
                    tabela?.let {
                        certificacao?.tabelas?.add(
                            it
                        )
                    }
//                    println("certificacao?.tabelas: ${certificacao?.tabelas}")
                }

                "grupo" -> {
                    grupo?.let {
                        roteiro?.grupos?.add(
                            it
                        )
                    }
//                    println("roteiro?.grupos: ${roteiro?.grupos}")
                }

                "testcase" -> {
                    testCase?.let {
                        grupo?.testCases?.add(
                            it
                        )
                    }
//                    println("grupo?.testCases: ${grupo?.testCases}")
                }

                "fluxo" -> {
                    fluxo?.let {
                        testCase?.fluxos?.add(
                            it
                        )
                    }
//                    println("testCase?.fluxos: ${testCase?.fluxos}")
                }

                "comando" -> {
                    comando?.let {
                        testCase?.comandos?.add(
                            it
                        )
                    }
//                    println("testCase?.comandos: ${testCase?.comandos}")
                }

                "parametro" -> {
                    parametro?.valor = valorAtual
                    parametro?.let {
                        comando?.parametros?.add(
                            it
                        )
                    }
//                    println("comando?.parametros: ${comando?.parametros}")
                }

                "procedimento" -> {
                    fluxo?.procedimento = Procedimento(valor = valorAtual)
                }

                "display" -> {
                    fluxo?.display = Display(valor = valorAtual)
                }

                "observacao" -> {
                    fluxo?.observacao = Observacao(valor = valorAtual)
                }

                "registro" -> {
                    registro.valor = valorAtual
                    registro.let {
                        tabela?.registros?.add(
                            it
                        )
                    }
//                    println("tabela?.registros: ${tabela?.registros}")
                }

            }
        }
    }

    fun main() {

        val factory = SAXParserFactory.newInstance()
        val saxParser = factory.newSAXParser()
        val handler = XmlHandler()

        val file = File("C:/Projetos/workspace/TrustTerm/composeApp/assets/Cert_Full_V2.12.xml")
        var totalTime = measureTime {
            saxParser.parse(file, handler)
        }
        println("Total Time XML = ${totalTime.inWholeMilliseconds}")

        totalTime = measureTime {
            val iLenRoteiros = certificacao?.roteiros?.size ?: 0
            for (iRoteiro in 0 until iLenRoteiros) {
                println("iRoteiro[$iRoteiro] name = ${certificacao?.roteiros?.get(iRoteiro)?.nome}")
                val iLenGrupos = certificacao?.roteiros?.get(iRoteiro)?.grupos?.size ?: 0
                for (iGrupo in 0 until iLenGrupos) {
                    println("\tiGrupo[$iGrupo] name = ${certificacao?.roteiros?.get(iRoteiro)?.grupos?.get(iGrupo)?.nome}")
                    val iLenTestCases = certificacao?.roteiros?.get(iRoteiro)?.grupos?.get(iGrupo)?.testCases?.size ?: 0
                    for (iTestCase in 0 until iLenTestCases) {
                        println(
                            "\t\tiTestCase[$iTestCase] name = ${
                                certificacao?.roteiros?.get(iRoteiro)?.grupos?.get(
                                    iGrupo
                                )?.testCases?.get(iTestCase)?.nome
                            }"
                        )
                    }
                }
            }

            val iLenTabelas = certificacao?.tabelas?.size ?: 0
            for (iTabela in 0 until iLenTabelas){
                println("iTabela[$iTabela] name = ${certificacao?.roteiros?.get(iTabela)?.nome}")
            }
        }
        println("Total Time Organize = ${totalTime.inWholeMilliseconds}")

    }
}