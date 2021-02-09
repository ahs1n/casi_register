package edu.aku.hassannaqvi.casi_register.base.repository

import edu.aku.hassannaqvi.casi_register.models.*
import kotlin.collections.ArrayList

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
//    suspend fun getSelectedChildList(cluster: String, hhno: String, uuid: String): ArrayList<ChildInformation>
    /*
    * For SectionH1 End
    * */

}