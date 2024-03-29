package edu.aku.hassannaqvi.casi_register.models

import android.database.Cursor
import android.provider.BaseColumns
import org.apache.commons.lang3.StringUtils
import org.json.JSONException
import org.json.JSONObject

/**
 * @author aliazaz.
 */
class Villages {
    var region = StringUtils.EMPTY
    var district = StringUtils.EMPTY
    var uc = StringUtils.EMPTY
    var village = StringUtils.EMPTY
    var country_code = StringUtils.EMPTY
    var country = StringUtils.EMPTY
    var district_code = StringUtils.EMPTY
    var uc_code = StringUtils.EMPTY
    var village_code = StringUtils.EMPTY
    var region_code = StringUtils.EMPTY
    var username = StringUtils.EMPTY

    @Throws(JSONException::class)
    fun sync(jsonObject: JSONObject): Villages {
        region = jsonObject.getString(VillagesTable.COLUMN_REGION)
        district = jsonObject.getString(VillagesTable.COLUMN_DISTRICT)
        uc = jsonObject.getString(VillagesTable.COLUMN_UC)
        village = jsonObject.getString(VillagesTable.COLUMN_VILLAGE)
        country_code = jsonObject.getString(VillagesTable.COLUMN_COUNTRY_CODE)
        country = jsonObject.getString(VillagesTable.COLUMN_COUNTRY)
        district_code = jsonObject.getString(VillagesTable.COLUMN_DISTRICT_CODE)
        uc_code = jsonObject.getString(VillagesTable.COLUMN_UC_CODE)
        village_code = jsonObject.getString(VillagesTable.COLUMN_VILLLAGE_CODE)
        region_code = jsonObject.getString(VillagesTable.COLUMN_REGION_CODE)
        username = jsonObject.getString(VillagesTable.COLUMN_USERNAME)
        return this
    }

    fun hydrate(cursor: Cursor): Villages {
        region = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_REGION))
        district = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_DISTRICT))
        uc = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_UC))
        village = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_VILLAGE))
        country_code = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_COUNTRY_CODE))
        country = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_COUNTRY))
        district_code = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_DISTRICT_CODE))
        uc_code = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_UC_CODE))
        village_code = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_VILLLAGE_CODE))
        region_code = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_REGION_CODE))
        username = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_USERNAME))
        return this
    }

    object VillagesTable : BaseColumns {
        const val TABLE_NAME = "villages"
        const val _ID = "_id"
        const val COLUMN_COUNTRY_CODE = "country_code"
        const val COLUMN_COUNTRY = "country_name"
        const val COLUMN_REGION_CODE = "region_code"
        const val COLUMN_REGION = "region"
        const val COLUMN_DISTRICT_CODE = "district_code"
        const val COLUMN_DISTRICT = "district"
        const val COLUMN_UC_CODE = "uc_code"
        const val COLUMN_UC = "uc"
        const val COLUMN_VILLLAGE_CODE = "village_code"
        const val COLUMN_VILLAGE = "village"
        const val COLUMN_USERNAME = "username"
    }
}