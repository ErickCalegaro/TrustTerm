package br.com.ic2tech.trustterm.model

class CertificationClass {

    companion object{
        var tCertification = CertificationType()

        fun getCertificationData(): CertificationType = tCertification

        fun setCertificationData(tNewCertification: CertificationType) {
            tCertification = tNewCertification
        }
    }
}