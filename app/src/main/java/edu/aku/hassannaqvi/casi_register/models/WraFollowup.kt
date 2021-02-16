package edu.aku.hassannaqvi.casi_register.models

import android.database.Cursor
import android.provider.BaseColumns
import edu.aku.hassannaqvi.casi_register.contracts.FormsContract
import org.apache.commons.lang3.StringUtils
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable

/**
 * @author aliazaz.
 */
class WraFollowup : Serializable {
    var id = StringUtils.EMPTY
    var lUID = StringUtils.EMPTY //UID
    var ws01 = StringUtils.EMPTY //country
    var ws01a = StringUtils.EMPTY //region
    var ws01b = StringUtils.EMPTY //district
    var ws04 = StringUtils.EMPTY //uc
    var ws05 = StringUtils.EMPTY //village
    var ws08 = StringUtils.EMPTY //Screening Date
    var ws09 = StringUtils.EMPTY //LHW No
    var ws10 = StringUtils.EMPTY //child reg
    var ws11 = StringUtils.EMPTY //child name
    var ws12 = StringUtils.EMPTY //father name

    //Not saving in DB
    var childTableDataExist = false //already exist followup form

    @Throws(JSONException::class)
    fun sync(jsonObject: JSONObject): WraFollowup {
        ws01 = jsonObject.getString(WraTable.COLUMN_WS01)
        lUID = jsonObject.getString(WraTable.COLUMN_LUID)
        ws01a = jsonObject.getString(WraTable.COLUMN_WS01A)
        ws01b = jsonObject.getString(WraTable.COLUMN_WS01B)
        ws04 = jsonObject.getString(WraTable.COLUMN_WS04)
        ws05 = jsonObject.getString(WraTable.COLUMN_WS05)
        ws08 = jsonObject.getString(WraTable.COLUMN_WS08)
        ws09 = jsonObject.getString(WraTable.COLUMN_WS09)
        ws10 = jsonObject.getString(WraTable.COLUMN_WS10)
        ws11 = jsonObject.getString(WraTable.COLUMN_WS11)
        ws12 = jsonObject.getString(WraTable.COLUMN_WS12)
        return this
    }

    fun hydrate(cursor: Cursor): WraFollowup {
        lUID = cursor.getString(cursor.getColumnIndex(WraTable.COLUMN_LUID))
        ws01 = cursor.getString(cursor.getColumnIndex(WraTable.COLUMN_WS01))
        ws01a = cursor.getString(cursor.getColumnIndex(WraTable.COLUMN_WS01A))
        ws01b = cursor.getString(cursor.getColumnIndex(WraTable.COLUMN_WS01B))
        ws09 = cursor.getString(cursor.getColumnIndex(WraTable.COLUMN_WS09))
        ws04 = cursor.getString(cursor.getColumnIndex(WraTable.COLUMN_WS04))
        ws05 = cursor.getString(cursor.getColumnIndex(WraTable.COLUMN_WS05))
        ws08 = cursor.getString(cursor.getColumnIndex(WraTable.COLUMN_WS08))
        ws10 = cursor.getString(cursor.getColumnIndex(WraTable.COLUMN_WS10))
        ws11 = cursor.getString(cursor.getColumnIndex(WraTable.COLUMN_WS11))
        ws12 = cursor.getString(cursor.getColumnIndex(WraTable.COLUMN_WS12))
        return this
    }

    fun hydrateForm(cursor: Cursor): WraFollowup {
        lUID = cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_UID))
        ws01 = cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_COUNTRY_CODE))
        ws01b = cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_DISTRICT_CODE))
        ws04 = cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_UC_CODE))
        ws05 = cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_VILLAGE_CODE))

        val form = Form()
        form.wSHydrate(cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_WS)))

        ws09 = form.ws09
        ws08 = form.ws08
        ws10 = form.ws10
        ws11 = form.ws11
        ws12 = form.ws12
        return this
    }

    object WraTable : BaseColumns {
        const val TABLE_NAME = "wraFollowup"
        const val _ID = "_id"
        const val COLUMN_LUID = "_uid"
        const val COLUMN_WS01 = "ws01"
        const val COLUMN_WS01A = "ws01a"
        const val COLUMN_WS01B = "ws01b"
        const val COLUMN_WS04 = "ws04"
        const val COLUMN_WS05 = "ws05"
        const val COLUMN_WS08 = "ws08"
        const val COLUMN_WS09 = "ws09"
        const val COLUMN_WS10 = "ws10"
        const val COLUMN_WS11 = "ws11"
        const val COLUMN_WS12 = "ws12"
    }
}