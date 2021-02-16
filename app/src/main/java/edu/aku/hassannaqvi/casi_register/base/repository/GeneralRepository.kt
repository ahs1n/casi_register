package edu.aku.hassannaqvi.casi_register.base.repository

import edu.aku.hassannaqvi.casi_register.database.DatabaseHelper
import edu.aku.hassannaqvi.casi_register.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.collections.ArrayList

open class GeneralRepository(private val db: DatabaseHelper) : GeneralDataSource {

    override suspend fun getSelectedServerChildList(country: String, identification: Identification): ArrayList<ChildFollowup> = withContext(Dispatchers.IO) {
        db.getChildrenFollowUpFromDB(country, identification)
    }

    override suspend fun getSelectedChildLocalFormList(country: String, identification: Identification): ArrayList<ChildFollowup> = withContext(Dispatchers.IO) {
        db.getChildrenFollowupFromFormDB(country, identification)
    }

    override suspend fun getLocalDBFollowupFormList(country: String, identification: Identification, reg_no: String, followupType: String): Form? = withContext(Dispatchers.IO) {
        db.getFollowUpFormStatus(country, identification, reg_no, followupType)
    }

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