package edu.aku.hassannaqvi.casi_register.base.repository

import edu.aku.hassannaqvi.casi_register.core.DatabaseHelper
import edu.aku.hassannaqvi.casi_register.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.collections.ArrayList

open class GeneralRepository(private val db: DatabaseHelper) : GeneralDataSource {

/*    override suspend fun getSelectedChildList(cluster: String, hhno: String, uuid: String): ArrayList<ChildInformation> = withContext(Dispatchers.IO) {
        db.getSelectedChildrenFromDB(cluster, hhno, uuid)
    }*/

    override suspend fun getLoginInformation(username: String, password: String): Users? = withContext(Dispatchers.IO) {
        db.getLoginUser(username, password)
    }

    override suspend fun getFormsByDate(date: String): ArrayList<Form> = withContext(Dispatchers.IO) {
        db.getFormsByDate(date)
    }

    override suspend fun getUploadStatus(): FormIndicatorsModel = withContext(Dispatchers.IO) {
        db.uploadStatusCount
    }

    override suspend fun getFormStatus(date: String): FormIndicatorsModel = withContext(Dispatchers.IO) {
        db.getFormStatusCount(date)
    }
}