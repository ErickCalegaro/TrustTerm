package br.com.ic2tech.trustterm.utils

import br.com.ic2tech.trustterm.getPlatform
import java.text.SimpleDateFormat
import java.util.*

class StringUtil {
    private fun Date.toString(format: String, locale: Locale = Locale.ITALY): String {
        val tFormatter = SimpleDateFormat(format, locale)
        return tFormatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    private fun getCurrentHour(): String {
        val tDate = getCurrentDateTime()
        val sHourInString = tDate.toString("HH")
        return sHourInString
    }

    fun getCurrentDateByTemplate(sTemplate: String): String {
        val tDate = getCurrentDateTime()
        val sDateInString = tDate.toString(sTemplate)
        return sDateInString
    }

    private fun getUsername(): String {
        val sUsername = "Erick" // TODO(Deixar dinamico)
        println("getPlatform().user = [${getPlatform().user}]")
        return sUsername
    }

    fun getWellcomeMessage(): String {
        val iHour = getCurrentHour().toInt()

        val sWellcomeMessage =
            if(iHour in 5..12) {
                "Bom dia, ${getUsername()}!"
            } else if(iHour in 13..18) {
                "Boa tarde, ${getUsername()}!"
            } else {
                "Boa noite, ${getUsername()}!"
            }

        return sWellcomeMessage
    }

}