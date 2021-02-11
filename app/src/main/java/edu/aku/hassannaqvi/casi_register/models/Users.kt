package edu.aku.hassannaqvi.casi_register.models

import android.database.Cursor
import org.apache.commons.lang3.StringUtils
import org.json.JSONException
import org.json.JSONObject

/**
 * @author hassan.naqvi.
 */
class Users {
    var userID: Long = 0
    var userName: String = StringUtils.EMPTY
    var password: String = StringUtils.EMPTY
    var fullname: String = StringUtils.EMPTY
    var country_id: String = StringUtils.EMPTY

    constructor() {
        // Default Constructor
    }

    constructor(username: String, fullname: String) {
        userName = username
        this.fullname = fullname
    }

    @Throws(JSONException::class)
    fun sync(jsonObject: JSONObject): Users {
        userName = jsonObject.getString(UsersTable.COLUMN_USERNAME)
        password = jsonObject.getString(UsersTable.COLUMN_PASSWORD)
        fullname = jsonObject.getString(UsersTable.COLUMN_FULLNAME)
        country_id = jsonObject.getString(UsersTable.COLUMN_COUNTRY_ID)
        return this
    }

    fun hydrate(cursor: Cursor): Users {
        userID = cursor.getLong(cursor.getColumnIndex(UsersTable.COLUMN_ID))
        userName = cursor.getString(cursor.getColumnIndex(UsersTable.COLUMN_USERNAME))
        password = cursor.getString(cursor.getColumnIndex(UsersTable.COLUMN_PASSWORD))
        fullname = cursor.getString(cursor.getColumnIndex(UsersTable.COLUMN_FULLNAME))
        country_id = cursor.getString(cursor.getColumnIndex(UsersTable.COLUMN_COUNTRY_ID))
        return this
    }

    object UsersTable {
        const val TABLE_NAME = "users"
        const val COLUMN_ID = "_id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_FULLNAME = "full_name"
        const val COLUMN_COUNTRY_ID = "country_id"
    }
}