package br.com.ic2tech.trustterm.model

class GuideClass {
    companion object{
        var tGuideList = CertificationClass.getCertificationData().atGuides

        fun getGuideList(): List<GuideType> = tGuideList
    }
}