package edu.aku.hassannaqvi.casi_register.models

import android.database.Cursor
import android.provider.BaseColumns
import org.apache.commons.lang3.StringUtils
import org.json.JSONException
import org.json.JSONObject

class HealthFacilitiy {
    var country_code: String = StringUtils.EMPTY
    var country_name: String = StringUtils.EMPTY
    var region_code: String = StringUtils.EMPTY
    var region: String = StringUtils.EMPTY
    var district_code: String = StringUtils.EMPTY
    var district: String = StringUtils.EMPTY
    var uc_code: String = StringUtils.EMPTY
    var uc: String = StringUtils.EMPTY
    var village_code: String = StringUtils.EMPTY
    var village: String = StringUtils.EMPTY

    @Throws(JSONException::class)
    fun sync(jsonObject: JSONObject): HealthFacilitiy {
        country_name = jsonObject.getString(HealthFacilityTable.COLUMN_COUNTRY_NAME)
        country_code = jsonObject.getString(HealthFacilityTable.COLUMN_COUNTRY_CODE)
        region = jsonObject.getString(HealthFacilityTable.COLUMN_REGION)
        region_code = jsonObject.getString(HealthFacilityTable.COLUMN_REGION_CODE)
        district = jsonObject.getString(HealthFacilityTable.COLUMN_DISTRICT)
        district_code = jsonObject.getString(HealthFacilityTable.COLUMN_DISTRICT_CODE)
        uc = jsonObject.getString(HealthFacilityTable.COLUMN_UC)
        uc_code = jsonObject.getString(HealthFacilityTable.COLUMN_UC_CODE)
        village = jsonObject.getString(HealthFacilityTable.COLUMN_VILLAGE)
        village_code = jsonObject.getString(HealthFacilityTable.COLUMN_VILLAGE_CODE)
        return this
    }

    fun hydrate(cursor: Cursor): HealthFacilitiy {
        country_name = cursor.getString(cursor.getColumnIndex(HealthFacilityTable.COLUMN_COUNTRY_NAME))
        country_code = cursor.getString(cursor.getColumnIndex(HealthFacilityTable.COLUMN_COUNTRY_CODE))
        district = cursor.getString(cursor.getColumnIndex(HealthFacilityTable.COLUMN_DISTRICT))
        district_code = cursor.getString(cursor.getColumnIndex(HealthFacilityTable.COLUMN_DISTRICT_CODE))
        uc = cursor.getString(cursor.getColumnIndex(HealthFacilityTable.COLUMN_UC))
        uc_code = cursor.getString(cursor.getColumnIndex(HealthFacilityTable.COLUMN_UC_CODE))
        village = cursor.getString(cursor.getColumnIndex(HealthFacilityTable.COLUMN_VILLAGE))
        village_code = cursor.getString(cursor.getColumnIndex(HealthFacilityTable.COLUMN_VILLAGE_CODE))
        region = cursor.getString(cursor.getColumnIndex(HealthFacilityTable.COLUMN_REGION))
        region_code = cursor.getString(cursor.getColumnIndex(HealthFacilityTable.COLUMN_REGION_CODE))
        return this
    }

    object HealthFacilityTable : BaseColumns {
        const val TABLE_NAME = "healthfacility"
        const val _ID = "_id"
        const val COLUMN_COUNTRY_CODE = "country_code"
        const val COLUMN_COUNTRY_NAME = "country"
        const val COLUMN_DISTRICT = "district"
        const val COLUMN_DISTRICT_CODE = "district_code"
        const val COLUMN_UC = "uc"
        const val COLUMN_UC_CODE = "uc_code"
        const val COLUMN_REGION = "region"
        const val COLUMN_REGION_CODE = "region_code"
        const val COLUMN_VILLAGE = "village"
        const val COLUMN_VILLAGE_CODE = "village_code"
    }
}