package br.com.ic2tech.trustterm

import br.com.ic2tech.trustterm.view.MainScreen
import androidx.compose.runtime.*
import br.com.ic2tech.trustterm.model.CertificationClass
import br.com.ic2tech.trustterm.model.ManagerXML
import br.com.ic2tech.trustterm.view.TestScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.time.measureTime

@Composable
fun App() {
/*
    try {
        val tXML = ManagerXML()
        tXML.parseFileToCertification("C:/Projetos/workspace/TrustTerm/composeApp/assets/Cert_Full_V2.12.xml")
    }catch (e: Exception){
        e.printStackTrace()
    }

    val tCurrCertification = CertificationClass.getCertificationData()
    val totalTime = measureTime {
        val iLenRoteiros = tCurrCertification.atGuides.size
        for (iRoteiro in 0 until iLenRoteiros) {
            println("iRoteiro[$iRoteiro] name = ${tCurrCertification.atGuides[iRoteiro].sName}")
            val iLenGrupos = tCurrCertification.atGuides[iRoteiro].atGroups.size
            for (iGrupo in 0 until iLenGrupos) {
                println("\tiGrupo[$iGrupo] name = ${tCurrCertification.atGuides[iRoteiro].atGroups[iGrupo].sName}")
                val iLenTestCases = tCurrCertification.atGuides[iRoteiro].atGroups[iGrupo].atTestCases.size
                for (iTestCase in 0 until iLenTestCases) {
                    println(
                        "\t\tiTestCase[$iTestCase] name = ${
                            tCurrCertification.atGuides[iRoteiro].atGroups[iGrupo].atTestCases[iTestCase].sName
                        }"
                    )
                }
            }
        }

        val iLenTabelas = tCurrCertification.atTables.size
        for (iTabela in 0 until iLenTabelas){
            println("iTabela[$iTabela] name = ${tCurrCertification.atGuides[iTabela].sName}")
        }
    }
    println("Total Time Organize = ${totalTime.inWholeMilliseconds}")
*/

    MainScreen()

//    val testScreen = TestScreen()
//    testScreen.TestScreen()

//    val controller = GuideController()
//    GuideListScreen(controller)

}