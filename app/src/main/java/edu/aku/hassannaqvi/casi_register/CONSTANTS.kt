package edu.aku.hassannaqvi.casi_register

class CONSTANTS {
    companion object {
        //For Login
        const val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0
        const val MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 2
        const val TWO_MINUTES = 1000 * 60 * 2
        const val MINIMUM_DISTANCE_CHANGE_FOR_UPDATES: Long = 1 // in Meters
        const val MINIMUM_TIME_BETWEEN_UPDATES: Long = 1000 // in Milliseconds
        const val DAYS_IN_A_MONTH = 30.4375

        //Login Result Code
        const val COUNTRY_CODE = "country_code"
        const val FIRST_TIME_INSTALL = "first_time_install"

        //SubInfo
        const val ITEM_DATA = "item_data"
        const val FOLLOWUP_FLAG = "followup_flag"

        //TYPE_COUNTERS
        const val WRA_TYPE = "M101"
        const val CHILD_TYPE = "C101"
        const val WRA_FOLLOWUP_TYPE = "M102"
        const val CHILD_FOLLOWUP_TYPE = "C102"

        //MainActivity
        const val DOWNLOAD_FILE_REFID = "downloadFileRefID"
        const val TAG_NAME = "tagName"
        const val BACKUP_DT = "backupDT"

        //COUNTRIES
        const val PAKISTAN = 1
        const val TAJIKISTAN = 3

    }
}