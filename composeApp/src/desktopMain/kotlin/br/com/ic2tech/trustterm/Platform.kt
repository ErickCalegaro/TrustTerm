package br.com.ic2tech.trustterm

class JVMPlatform {
    val name: String = "Java ${System.getProperty("java.version")}"
    val user: String = "Java ${System.getProperty("user.name")}"
}

fun getPlatform() = JVMPlatform()