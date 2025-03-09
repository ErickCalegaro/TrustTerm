package br.com.ic2tech.trustterm.model
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.File
import javax.xml.parsers.SAXParserFactory
import kotlin.time.Duration
import kotlin.time.measureTime

class ManagerXML {

    class XmlHandler : DefaultHandler() {
        private var tCertification: CertificationType? = null
        private var sCurrElement = ""
        private var tGuide: GuideType? = null
        private var tTable: TableType? = null
        private var tGroup: GroupType? = null
        private var tTestCase: TestCaseType? = null
        private var tFlow: FlowType? = null
        private var tProcedure = ProcedureType(sValue = "")
        private var tDisplay = DisplayType(sValue = "")
        private var tNote = NoteType(sValue = "")
        private var tRegister = RegisterType(codigo = "", valor = "")
        private var tCommand: CommandType? = null
        private var tParameter: ParameterType? = null
        private var sCurrValue = ""

        override fun startElement(uri: String?, localName: String?, qName: String, attributes: Attributes) {
            sCurrElement = qName

            when (qName) {
                "certificacao" -> {
                    tCertification = CertificationType(
                        sName = attributes.getValue("nome") ?: "",
                        sId = attributes.getValue("Id") ?: "",
                        sSolution = attributes.getValue("solucao") ?: "",
                        sVersion = attributes.getValue("versaoSW") ?: "",
                        sCicle = attributes.getValue("ciclo") ?: "",
                        sSpec = attributes.getValue("espec") ?: "",
                        sUser = attributes.getValue("usuario") ?: "",
                        sEntity = attributes.getValue("entidade") ?: "",
                        sStatus = attributes.getValue("statusExecucao") ?: ""
                    )
                    tCertification?.atGuides = mutableListOf()
                    tCertification?.atTables = mutableListOf()
                }

                "roteiro" -> {
                    tGuide = GuideType(
                        sId = attributes.getValue("Id") ?: "",
                        sName = attributes.getValue("nome") ?: "",
                        sVersion = attributes.getValue("versao") ?: ""
                    )
                }

                "tabela" -> {
                    tTable = TableType(
                        sId = attributes.getValue("Id") ?: "",
                        sName = attributes.getValue("nome") ?: "",
                        aAcqIdx = attributes.getValue("acqIdx") ?: "",
                        sTableVersion = attributes.getValue("tabVer") ?: "",
                        sVersion = attributes.getValue("versao") ?: ""
                    )
                }

                "grupo" -> {
                    tGroup = GroupType(
                        sId = attributes.getValue("Id") ?: "",
                        sName = attributes.getValue("nome") ?: "",
                        sTableName = attributes.getValue("tabelaNome") ?: "",
                        sTableVersion = attributes.getValue("tabelaVersao") ?: ""
                    )
                }

                "testcase" -> {
                    tTestCase = TestCaseType(
                        sId = attributes.getValue("Id") ?: "",
                        sName = attributes.getValue("nome") ?: "",
                        sDescription = attributes.getValue("descricao") ?: "",
                        sVersion = attributes.getValue("versao") ?: "",
                        sScript = attributes.getValue("script") ?: ""
                    )
                }

                "fluxo" -> {
                    tFlow = FlowType(
                        sId = attributes.getValue("Id") ?: "",
                        sItemId = attributes.getValue("itemId") ?: "",
                        sProcedure = ProcedureType(sValue = ""),
                        sDisplay = DisplayType(sValue = ""),
                        sNote = NoteType(sValue = "")
                    )
                }

                "comando" -> {
                    tCommand = CommandType(
                        sId = attributes.getValue("Id") ?: "",
                        sItemId = attributes.getValue("itemId") ?: "",
                        sName = attributes.getValue("nome") ?: ""
                    )
                }

                "parametro" -> {
                    tParameter = ParameterType(
                        sId = attributes.getValue("Id") ?: "",
                        sName = attributes.getValue("nome") ?: "",
                        sType = attributes.getValue("tipo") ?: "",
                        sValue = ""
                    )
                    sCurrValue = ""
                }

                "procedimento" -> {
                    tProcedure = ProcedureType(
                        sValue = ""
                    )
                    sCurrValue = ""
                }

                "display" -> {
                    tDisplay = DisplayType(
                        sValue = ""
                    )
                    sCurrValue = ""
                }

                "observacao" -> {
                    tNote = NoteType(
                        sValue = ""
                    )
                    sCurrValue = ""
                }

                "registro" -> {
                    tRegister = RegisterType(
                        codigo = attributes.getValue("codigo") ?: "",
                        valor = ""
                    )
                    sCurrValue = ""
                }
            }
        }

        override fun characters(ch: CharArray, start: Int, length: Int) {
            sCurrValue += String(ch, start, length).trim()
        }

        override fun endElement(uri: String?, localName: String?, qName: String) {
            when (qName) {

                "certificacao" -> {
                    tCertification?.let { CertificationClass.setCertificationData(it) }
//                    println("certificacao: ${certificacao}")
                }

                "roteiro" -> {
                    tGuide?.let {
                        tCertification?.atGuides?.add(
                            it
                        )
                    }
//                    println("certificacao?.roteiros: ${certificacao?.roteiros}")
                }

                "tabela" -> {
                    tTable?.let {
                        tCertification?.atTables?.add(
                            it
                        )
                    }
//                    println("certificacao?.tabelas: ${certificacao?.tabelas}")
                }

                "grupo" -> {
                    tGroup?.let {
                        tGuide?.atGroups?.add(
                            it
                        )
                    }
//                    println("roteiro?.grupos: ${roteiro?.grupos}")
                }

                "testcase" -> {
                    tTestCase?.let {
                        tGroup?.atTestCases?.add(
                            it
                        )
                    }
//                    println("grupo?.testCases: ${grupo?.testCases}")
                }

                "fluxo" -> {
                    tFlow?.let {
                        tTestCase?.atFlows?.add(
                            it
                        )
                    }
//                    println("testCase?.fluxos: ${testCase?.fluxos}")
                }

                "comando" -> {
                    tCommand?.let {
                        tTestCase?.atCommands?.add(
                            it
                        )
                    }
//                    println("testCase?.comandos: ${testCase?.comandos}")
                }

                "parametro" -> {
                    tParameter?.sValue = sCurrValue
                    tParameter?.let {
                        tCommand?.sParameters?.add(
                            it
                        )
                    }
//                    println("comando?.parametros: ${comando?.parametros}")
                }

                "procedimento" -> {
                    tFlow?.sProcedure = ProcedureType(sValue = sCurrValue)
                }

                "display" -> {
                    tFlow?.sDisplay = DisplayType(sValue = sCurrValue)
                }

                "observacao" -> {
                    tFlow?.sNote = NoteType(sValue = sCurrValue)
                }

                "registro" -> {
                    tRegister.valor = sCurrValue
                    tRegister.let {
                        tTable?.atRegisters?.add(
                            it
                        )
                    }
//                    println("tabela?.registros: ${tabela?.registros}")
                }

            }
        }
    }

    fun parseFileToCertification(sPathName: String) {

        val tFactory = SAXParserFactory.newInstance()
        val tSaxParser = tFactory.newSAXParser()
        val tHandler = XmlHandler()

        val iTotalTime : Duration

        try {
            val hFile = File(sPathName)
            iTotalTime = measureTime {
                tSaxParser.parse(hFile, tHandler)
            }
            println("Total Time XML = ${iTotalTime.inWholeMilliseconds}")
        }catch (e: Exception){
            e.printStackTrace()
            return
        }

    }
}