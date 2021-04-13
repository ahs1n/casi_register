package edu.aku.hassannaqvi.casi_register.models

import android.database.Cursor
import android.provider.BaseColumns
import edu.aku.hassannaqvi.casi_register.CONSTANTS
import edu.aku.hassannaqvi.casi_register.contracts.FormsContract
import edu.aku.hassannaqvi.casi_register.utils.getDOB
import org.apache.commons.lang3.StringUtils
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable

/**
 * @author aliazaz.
 */
class ChildFollowup : Serializable {
    var id = StringUtils.EMPTY
    var lUID = StringUtils.EMPTY //UID
    var cs01 = StringUtils.EMPTY //country
    var cs01a = StringUtils.EMPTY //region
    var cs01b = StringUtils.EMPTY //district
    var cs04 = StringUtils.EMPTY //uc
    var cs05 = StringUtils.EMPTY //village
    var cs08 = StringUtils.EMPTY //Screening Date
    var cs09 = StringUtils.EMPTY //LHW No
    var cs10 = StringUtils.EMPTY //child reg
    var cs10a = StringUtils.EMPTY //child mr no
    var cs11 = StringUtils.EMPTY //child name
    var cs11a = StringUtils.EMPTY //mother name
    var cs12 = StringUtils.EMPTY //father name
    var cs13 = StringUtils.EMPTY //gender
    var fupDt = StringUtils.EMPTY //fupDt
    var fupNo = StringUtils.EMPTY //fupNo
    var dob = StringUtils.EMPTY //dob

    //Not saving in DB
    var childTableDataExist = false //already exist followup form


    constructor(form: Form) {
        this.lUID = form._UID
        this.cs01 = form.cs01
        this.cs01a = form.cs01a
        this.cs01b = form.cs01b
        this.cs04 = form.cs04
        this.cs05 = form.cs05
        this.cs08 = form.cs08
        this.cs09 = form.cs09
        this.cs10 = form.cs10
        this.cs10a = form.cs10a
        this.cs11 = form.cs11
        this.cs11a = form.cs11a
        this.cs12 = form.cs12
        this.cs13 = form.cs13
        this.fupDt = "0"
        this.fupNo = "0"

        if (form.cs1403 == "9999") {
            val dt = getDOB("yyyy-MM-dd", form.cs1501.toInt(), form.cs1502.toInt(), 15)
            this.dob = dt
        } else {
            this.dob = form.cs1403 + "-" + form.cs1402 + "-" + form.cs1401
        }
    }

    constructor()


    @Throws(JSONException::class)
    fun sync(jsonObject: JSONObject): ChildFollowup {
        cs01 = jsonObject.getString(ChildTable.COLUMN_CS01)
        lUID = jsonObject.getString(ChildTable.COLUMN_LUID)
        cs01a = jsonObject.getString(ChildTable.COLUMN_CS01A)
        cs01b = jsonObject.getString(ChildTable.COLUMN_CS01B)
        cs04 = jsonObject.getString(ChildTable.COLUMN_CS04)
        cs05 = jsonObject.getString(ChildTable.COLUMN_CS05)
        cs08 = jsonObject.getString(ChildTable.COLUMN_CS08)
        cs09 = jsonObject.getString(ChildTable.COLUMN_CS09)
        cs10 = jsonObject.getString(ChildTable.COLUMN_CS10)
        cs10a = jsonObject.getString(ChildTable.COLUMN_CS10A)
        cs11 = jsonObject.getString(ChildTable.COLUMN_CS11)
        cs11a = jsonObject.getString(ChildTable.COLUMN_CS11A)
        cs12 = jsonObject.getString(ChildTable.COLUMN_CS12)
        cs13 = jsonObject.getString(ChildTable.COLUMN_CS13)
        fupDt = jsonObject.getString(ChildTable.COLUMN_FUPDT)
        fupNo = jsonObject.getString(ChildTable.COLUMN_FUPNO)
        dob = JSONObject(jsonObject.getString(ChildTable.COLUMN_DOB)).getString("date").substring(0, "yyyy-mm-dd".length)
        return this
    }

    fun hydrate(cursor: Cursor): ChildFollowup {
        lUID = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_LUID))
        cs01 = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_CS01))
        cs01a = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_CS01A))
        cs01b = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_CS01B))
        cs09 = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_CS09))
        cs04 = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_CS04))
        cs05 = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_CS05))
        cs08 = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_CS08))
        cs10 = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_CS10))
        cs11 = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_CS11))
        cs11a = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_CS11A))
        cs12 = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_CS12))
        cs13 = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_CS13))
        fupDt = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_FUPDT))
        fupNo = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_FUPNO))
        dob = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_DOB))
        return this
    }

    fun hydrateForm(cursor: Cursor): ChildFollowup {
        lUID = cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_UID))
        cs01 = cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_COUNTRY_CODE))
        cs01b = cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_DISTRICT_CODE))
        cs04 = cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_UC_CODE))
        cs05 = cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_VILLAGE_CODE))

        val form = Form()
        form.cSHydrate(cursor.getString(cursor.getColumnIndex(FormsContract.FormsTable.COLUMN_CS)))

        cs09 = form.cs09
        cs08 = form.cs08
        cs10 = form.cs10
        cs10a = form.cs10a
        cs11 = form.cs11
        cs11a = form.cs11a
        cs12 = form.cs12
        cs13 = form.cs13
        return this
    }



    object ChildTable : BaseColumns {
        const val TABLE_NAME = "childFollowups"
        const val _ID = "_id"
        const val COLUMN_LUID = "_uid"
        const val COLUMN_CS01 = "cs01"
        const val COLUMN_CS01A = "cs01a"
        const val COLUMN_CS01B = "cs01b"
        const val COLUMN_CS04 = "cs04"
        const val COLUMN_CS05 = "cs05"
        const val COLUMN_CS08 = "cs08"
        const val COLUMN_CS09 = "cs09"
        const val COLUMN_CS10 = "cs10"
        const val COLUMN_CS10A = "cs10a"
        const val COLUMN_CS11 = "cs11"
        const val COLUMN_CS11A = "cs11a"
        const val COLUMN_CS12 = "cs12"
        const val COLUMN_CS13 = "cs13"
        const val COLUMN_FUPDT = "fupdt"
        const val COLUMN_FUPNO = "fupNo"
        const val COLUMN_DOB = "dob"
    }
}