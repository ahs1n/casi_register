package edu.aku.hassannaqvi.casi_register.models

import android.database.Cursor
import android.provider.BaseColumns
import org.apache.commons.lang3.StringUtils
import org.json.JSONException
import org.json.JSONObject

class VersionApp {
    var versioncode= StringUtils.EMPTY
    var versionname= StringUtils.EMPTY
    var pathname= StringUtils.EMPTY

    @Throws(JSONException::class)
    fun sync(jsonObject: JSONObject): VersionApp {
        versioncode = jsonObject.getString(VersionAppTable.COLUMN_VERSION_CODE)
        pathname = jsonObject.getString(VersionAppTable.COLUMN_PATH_NAME)
        versionname = jsonObject.getString(VersionAppTable.COLUMN_VERSION_NAME)
        return this
    }

    fun hydrate(cursor: Cursor): VersionApp {
        versioncode = cursor.getString(cursor.getColumnIndex(VersionAppTable.COLUMN_VERSION_CODE))
        pathname = cursor.getString(cursor.getColumnIndex(VersionAppTable.COLUMN_PATH_NAME))
        versionname = cursor.getString(cursor.getColumnIndex(VersionAppTable.COLUMN_VERSION_NAME))
        return this
    }

    object VersionAppTable : BaseColumns {
        const val TABLE_NAME = "versionApp"
        const val COLUMN_ID = "_id"
        const val COLUMN_VERSION_PATH = "elements"
        const val COLUMN_VERSION_CODE = "versionCode"
        const val COLUMN_VERSION_NAME = "versionName"
        const val COLUMN_PATH_NAME = "outputFile"
        const val SERVER_URI = "output.json"
    }
}