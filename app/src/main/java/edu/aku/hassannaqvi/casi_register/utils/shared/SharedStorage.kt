package edu.aku.hassannaqvi.casi_register.utils.shared

import android.content.Context
import edu.aku.hassannaqvi.casi_register.CONSTANTS.Companion.COUNTRY_CODE
import edu.aku.hassannaqvi.casi_register.CONSTANTS.Companion.FIRST_TIME_INSTALL
import org.apache.commons.lang3.StringUtils

/*
* @author Ali.Azaz
* */
object SharedStorage : SharedStorageBase() {

    fun setLastRegistrationID(context: Context, villageCode: String, currentID: String) {
        put(context, villageCode, currentID)
    }

    fun getLastRegistrationID(context: Context, villageCode: String): String {
        return get(context, villageCode, StringUtils.EMPTY) as String
    }

    fun setCountryCode(context: Context, country: Int) {
        put(context, COUNTRY_CODE, country)
    }

    fun getCountryCode(context: Context): Int {
        return get(context, COUNTRY_CODE, 0) as Int
    }

    fun setFirstInstallFlag(context: Context, flag: Boolean) {
        put(context, FIRST_TIME_INSTALL, flag)
    }

    fun getFirstInstallFlag(context: Context): Boolean {
        return get(context, FIRST_TIME_INSTALL, true) as Boolean
    }

}