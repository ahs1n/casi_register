package edu.aku.hassannaqvi.casi_register.utils.shared

import android.content.Context
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

}