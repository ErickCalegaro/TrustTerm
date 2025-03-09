package br.com.ic2tech.trustterm.controller

import br.com.ic2tech.trustterm.model.GuideClass
import br.com.ic2tech.trustterm.model.GuideType

class GuideController {
    fun fetchGuides(): List<GuideType> {
        return GuideClass.getGuideList()
    }
}