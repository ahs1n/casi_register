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
    var ws10 = StringUtils.EMPTY //wra reg
    var ws11 = StringUtils.EMPTY //wra name
    var ws12 = StringUtils.EMPTY //father name
    var ws12a = StringUtils.EMPTY // Contact Number
    var ws13 = StringUtils.EMPTY //Age
    var fupDt = StringUtils.EMPTY //fupDT
    var fupNo = StringUtils.EMPTY //fupNo

    //Not saving in DB
    var wraTableDataExist = false //already exist followup form

    constructor(form: Form) {
        this.lUID = form._UID
        this.ws01 = form.ws01
        this.ws01a = form.ws01a
        this.ws01b = form.ws01b
        this.ws04 = form.ws04
        this.ws05 = form.ws05
        this.ws08 = form.ws08
        this.ws09 = form.ws09
        this.ws10 = form.ws10
        this.ws11 = form.ws11
        this.ws12 = form.ws12
        this.ws12a = form.ws12a
        this.ws13 = form.ws13
        this.fupDt = "0"
        this.fupNo = "0"
    }

    constructor()


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
        ws12a = jsonObject.getString(WraTable.COLUMN_WS12A)
        ws13 = jsonObject.getString(WraTable.COLUMN_WS13)
        fupDt = jsonObject.getString(WraTable.COLUMN_FUPDT)
        fupNo = jsonObject.getString(WraTable.COLUMN_FUPNO)
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
        ws12a = cursor.getString(cursor.getColumnIndex(WraTable.COLUMN_WS12A))
        ws13 = cursor.getString(cursor.getColumnIndex(WraTable.COLUMN_WS13))
        fupDt = cursor.getString(cursor.getColumnIndex(WraTable.COLUMN_FUPDT))
        fupNo = cursor.getString(cursor.getColumnIndex(WraTable.COLUMN_FUPNO))
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

        ws08 = form.ws08
        ws09 = form.ws09
        ws10 = form.ws10
        ws11 = form.ws11
        ws12 = form.ws12
        ws12a = form.ws12a
        ws13 = form.ws13
        return this
    }

    object WraTable : BaseColumns {
        const val TABLE_NAME = "mwraFollowups"
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
        const val COLUMN_WS12A = "ws12a"
        const val COLUMN_WS13 = "ws13"
        const val COLUMN_FUPDT = "fupdt"
        const val COLUMN_FUPNO = "fupNo"
    }
}