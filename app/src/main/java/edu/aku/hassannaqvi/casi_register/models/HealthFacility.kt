package edu.aku.hassannaqvi.casi_register.models

import android.database.Cursor
import android.provider.BaseColumns
import org.apache.commons.lang3.StringUtils
import org.json.JSONException
import org.json.JSONObject

class HealthFacility {
    var country_code = StringUtils.EMPTY
    var region_code = StringUtils.EMPTY
    var hf_code = StringUtils.EMPTY
    var health_facility = StringUtils.EMPTY
    var facility_type = StringUtils.EMPTY
    var dist_code = StringUtils.EMPTY
    var uc_code = StringUtils.EMPTY

    @Throws(JSONException::class)
    fun sync(jsonObject: JSONObject): HealthFacility {
        country_code = jsonObject.getString(HealthFacilityTable.COLUMN_COUNTRY_CODE)
        region_code = jsonObject.getString(HealthFacilityTable.COLUMN_REGION_CODE)
        hf_code = jsonObject.getString(HealthFacilityTable.COLUMN_HF_CODE)
        health_facility = jsonObject.getString(HealthFacilityTable.COLUMN_HEALTH_FACILITY)
        facility_type = jsonObject.getString(HealthFacilityTable.COLUMN_FACILITY_TYPE)
        dist_code = jsonObject.getString(HealthFacilityTable.COLUMN_DIST_CODE)
        uc_code = jsonObject.getString(HealthFacilityTable.COLUMN_UC_CODE)
        return this
    }

    fun hydrate(cursor: Cursor): HealthFacility {
        country_code = cursor.getString(cursor.getColumnIndex(HealthFacilityTable.COLUMN_COUNTRY_CODE))
        region_code = cursor.getString(cursor.getColumnIndex(HealthFacilityTable.COLUMN_REGION_CODE))
        hf_code = cursor.getString(cursor.getColumnIndex(HealthFacilityTable.COLUMN_HF_CODE))
        health_facility = cursor.getString(cursor.getColumnIndex(HealthFacilityTable.COLUMN_HEALTH_FACILITY))
        facility_type = cursor.getString(cursor.getColumnIndex(HealthFacilityTable.COLUMN_FACILITY_TYPE))
        dist_code = cursor.getString(cursor.getColumnIndex(HealthFacilityTable.COLUMN_DIST_CODE))
        uc_code = cursor.getString(cursor.getColumnIndex(HealthFacilityTable.COLUMN_UC_CODE))
        return this
    }

    object HealthFacilityTable : BaseColumns {
        const val TABLE_NAME = "healthfacility"
        const val _ID = "_id"
        const val COLUMN_COUNTRY_CODE = "country_code"
        const val COLUMN_REGION_CODE = "region_code"
        const val COLUMN_HF_CODE = "hf_code"
        const val COLUMN_HEALTH_FACILITY = "health_facility"
        const val COLUMN_FACILITY_TYPE = "facility_type"
        const val COLUMN_DIST_CODE = "dist_code"
        const val COLUMN_UC_CODE = "uc_code"
    }
}