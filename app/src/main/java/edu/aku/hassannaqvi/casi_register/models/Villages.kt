package edu.aku.hassannaqvi.casi_register.models

import android.content.ContentResolver
import android.content.ContentUris
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns
import org.apache.commons.lang3.StringUtils
import org.json.JSONException
import org.json.JSONObject

/**
 * @author aliazaz.
 */
class Villages {
    var country: String = StringUtils.EMPTY
    var district: String = StringUtils.EMPTY
    var uc: String = StringUtils.EMPTY
    var village: String = StringUtils.EMPTY
    var country_code: String = StringUtils.EMPTY
    var district_code: String = StringUtils.EMPTY
    var uc_code: String = StringUtils.EMPTY
    var villlage_code: String = StringUtils.EMPTY
    var cluster_no: String = StringUtils.EMPTY

    @Throws(JSONException::class)
    fun sync(jsonObject: JSONObject): Villages {
        country = jsonObject.getString(VillagesTable.COLUMN_COUNTRY)
        district = jsonObject.getString(VillagesTable.COLUMN_DISTRICT)
        uc = jsonObject.getString(VillagesTable.COLUMN_UC)
        village = jsonObject.getString(VillagesTable.COLUMN_VILLAGE)
        country_code = jsonObject.getString(VillagesTable.COLUMN_COUNTRY_CODE)
        district_code = jsonObject.getString(VillagesTable.COLUMN_DISTRICT_CODE)
        uc_code = jsonObject.getString(VillagesTable.COLUMN_UC_CODE)
        villlage_code = jsonObject.getString(VillagesTable.COLUMN_VILLLAGE_CODE)
        cluster_no = jsonObject.getString(VillagesTable.COLUMN_CLUSTER_NO)
        return this
    }

    fun hydrate(cursor: Cursor): Villages {
        country = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_COUNTRY))
        district = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_DISTRICT))
        uc = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_UC))
        village = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_VILLAGE))
        country_code = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_COUNTRY_CODE))
        district_code = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_DISTRICT_CODE))
        uc_code = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_UC_CODE))
        villlage_code = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_VILLLAGE_CODE))
        cluster_no = cursor.getString(cursor.getColumnIndex(VillagesTable.COLUMN_CLUSTER_NO))
        return this
    }

    object VillagesTable : BaseColumns {
        const val TABLE_NAME = "villages"
        const val _ID = "_id"
        const val COLUMN_COUNTRY = "country"
        const val COLUMN_DISTRICT = "district"
        const val COLUMN_UC = "uc"
        const val COLUMN_VILLAGE = "village"
        const val COLUMN_COUNTRY_CODE = "country_code"
        const val COLUMN_DISTRICT_CODE = "district_code"
        const val COLUMN_UC_CODE = "uc_code"
        const val COLUMN_VILLLAGE_CODE = "villlage_code"
        const val COLUMN_CLUSTER_NO = "cluster_no"
        const val SERVER_URI = "villages.php"
        var PATH = "villages"
    }
}