package br.com.ic2tech.trustterm.model

data class CertificationType(
    val sId: String,
    val sName: String,
    val sSolution: String,
    val sVersion: String,
    val sCicle: String,
    val sSpec: String,
    val sUser: String,
    val sEntity: String,
    val sStatus: String,
    var atGuides: MutableList<GuideType> = mutableListOf(),
    var atTables: MutableList<TableType> = mutableListOf()
) {
    constructor() : this(sId = "", sName = "", sSolution = "", sVersion = "", sCicle = "", sSpec = "", sUser = "", sEntity = "", sStatus = "")
}

data class GuideType(
    val sId: String,
    val sName: String,
    val sVersion: String,
    var atGroups: MutableList<GroupType> = mutableListOf()
)

data class TableType(
    val sId: String,
    val sName: String,
    val aAcqIdx: String,
    val sTableVersion: String,
    val sVersion: String,
    var atRegisters: MutableList<RegisterType> = mutableListOf()
)

data class GroupType(
    val sId: String,
    val sName: String,
    val sTableName: String,
    val sTableVersion: String,
    val atTestCases: MutableList<TestCaseType> = mutableListOf()
)

data class TestCaseType(
    val sId: String,
    val sName: String,
    val sDescription: String,
    val sVersion: String,
    val sScript: String,
    val atFlows: MutableList<FlowType> = mutableListOf(),
    val atCommands: MutableList<CommandType> = mutableListOf()

)

data class FlowType(
    val sId: String,
    val sItemId: String,
    var sProcedure: ProcedureType,
    var sDisplay: DisplayType,
    var sNote: NoteType
)

data class CommandType(
    val sId: String,
    val sItemId: String,
    val sName: String,
    val sParameters: MutableList<ParameterType> = mutableListOf()
)

data class ParameterType(
    val sId: String,
    val sType: String,
    val sName: String,
    var sValue: String
)

data class ProcedureType(
    val sValue: String
)

data class DisplayType(
    val sValue: String
)

data class NoteType(
    val sValue: String
)

data class RegisterType(
    val codigo: String,
    var valor: String
)