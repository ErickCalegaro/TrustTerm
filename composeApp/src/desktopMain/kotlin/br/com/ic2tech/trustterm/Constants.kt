package br.com.ic2tech.trustterm

const val PANTONE_CUSTOM        = 0xFF2C3E50
const val PANTONE_OFFWHITE      = 0xFFF2F0EF
const val PANTONE_OFFBLACK      = 0xFF343231
const val PANTONE_PASTEL_GREEN  = 0xFF50EE50
const val PANTONE_PASTEL_ORANGE = 0xFFFFA723
const val PANTONE_PASTEL_BLUE   = 0xFF557EBF
const val PANTONE_MIDDLE_GREY   = 0xFF485459


enum class NavigateTo {
    HOME,
    TEST_RUN,
    TEST_IMPORT,
    TEST_EXPORT,
    REPORT_DETAILED,
    REPORT_SIMPLIFIED,
    // ...
    LOGOUT,
    HIDE_MENU,
    NONE
}