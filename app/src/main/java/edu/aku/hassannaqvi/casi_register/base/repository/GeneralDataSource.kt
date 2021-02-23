package edu.aku.hassannaqvi.casi_register.base.repository

import edu.aku.hassannaqvi.casi_register.models.*
import org.json.JSONArray

interface GeneralDataSource {

    /*
    * For login Start
    * */
    suspend fun getLoginInformation(username: String, password: String): Users?
    /*
    * For login End
    * */

    /*
    * For MainActivity Start
    * */
    suspend fun getFormsByDate(date: String): ArrayList<Form>

    suspend fun getUploadStatus(): FormIndicatorsModel

    suspend fun getFormStatus(date: String): FormIndicatorsModel
    /*
    * For MainActivity End
    * */

    /*
    * For Section Selected ChildList
    * */
    suspend fun getSelectedServerChildList(country: String, identification: Identification): ArrayList<ChildFollowup>

    suspend fun getSelectedChildLocalFormList(country: String, identification: Identification): ArrayList<ChildFollowup>

    suspend fun getSelectedServerWraList(country: String, identification: Identification): ArrayList<WraFollowup>

    suspend fun getSelectedWraLocalFormList(country: String, identification: Identification): ArrayList<WraFollowup>

    suspend fun getLocalDBFollowupFormList(country: String, identification: Identification, reg_no: String, followupType: String): Form?
    /*
    * For SectionH1 End
    * */

    /*
    * For Splash Start
    * */
    suspend fun insertZScore(date: JSONArray): Int
    /*
    * For Splash End
    * */

}