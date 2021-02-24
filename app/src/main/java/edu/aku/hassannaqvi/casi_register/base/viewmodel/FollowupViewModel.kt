package edu.aku.hassannaqvi.casi_register.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.aku.hassannaqvi.casi_register.CONSTANTS
import edu.aku.hassannaqvi.casi_register.base.repository.GeneralRepository
import edu.aku.hassannaqvi.casi_register.base.repository.ResponseStatusCallbacks
import edu.aku.hassannaqvi.casi_register.models.ChildFollowup
import edu.aku.hassannaqvi.casi_register.models.Identification
import edu.aku.hassannaqvi.casi_register.models.WraFollowup
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FollowupViewModel(internal val repository: GeneralRepository) : ViewModel() {

    private val _childResponse: MutableLiveData<ResponseStatusCallbacks<List<ChildFollowup>>> = MutableLiveData()

    val childResponse: MutableLiveData<ResponseStatusCallbacks<List<ChildFollowup>>>
        get() = _childResponse

    private val _wraResponse: MutableLiveData<ResponseStatusCallbacks<List<WraFollowup>>> = MutableLiveData()

    val wraResponse: MutableLiveData<ResponseStatusCallbacks<List<WraFollowup>>>
        get() = _wraResponse


    fun getChildDataFromDB(country: String, identification: Identification, filterName: String? = null) {
        _childResponse.value = ResponseStatusCallbacks.loading(null)
        viewModelScope.launch {
            try {
                delay(1000)
                val children: ArrayList<ChildFollowup> = ArrayList()

                val first = async { children.addAll(repository.getSelectedServerChildList(country, identification)) }
                first.await()

                /*val localChildren: ArrayList<ChildFollowup> = ArrayList()
                val second = async { repository.getSelectedChildLocalFormList(country, identification) }
                second.await().let { it ->
                    it.forEach {
                        if (children.find { item -> item.cs10 == it.cs10 } == null)
                            localChildren.add(it)
                    }
                }
                children.addAll(localChildren)*/

                _childResponse.value = if (children.size > 0) {

                    val third = launch {
                        children.forEachIndexed { index, item ->
                            val form = repository.getLocalDBFollowupFormList(country, identification, item.cs10, CONSTANTS.CHILD_FOLLOWUP_TYPE)
                            form?.let {
                                item.childTableDataExist = true
                                children[index] = item
                            }
                        }
                    }
                    third.join()

                    val childList = ArrayList<ChildFollowup>(
                            {
                                val child = children.sortedBy { it.cs11 }
                                if (filterName != null)
                                    child.filter {
                                        it.cs11.startsWith(filterName)
                                    }
                                else child
                            }())

                    if (childList.size> 0) ResponseStatusCallbacks.success(data = childList, message = "Child list found")
                    else ResponseStatusCallbacks.error(data = null, message = "No child found!")
                } else
                    ResponseStatusCallbacks.error(data = null, message = "No child found!")
            } catch (e: Exception) {
                _childResponse.value =
                        ResponseStatusCallbacks.error(data = null, message = e.message.toString())
            }

        }

    }

    fun getWraDataFromDB(country: String, identification: Identification) {
        _wraResponse.value = ResponseStatusCallbacks.loading(null)
        viewModelScope.launch {
            try {
                delay(1000)
                val wra: ArrayList<WraFollowup> = ArrayList()

                val first = async { wra.addAll(repository.getSelectedServerWraList(country, identification)) }
                first.await()

                /*val localWra: ArrayList<WraFollowup> = ArrayList()
                val second = async { repository.getSelectedWraLocalFormList(country, identification) }
                second.await().let { it ->
                    it.forEach {
                        if (wra.find { item -> item.ws10 == it.ws10 } == null)
                            localWra.add(it)
                    }
                }
                wra.addAll(localWra)*/

                _wraResponse.value = if (wra.size > 0) {

                    val third = launch {
                        wra.forEachIndexed { index, item ->
                            val form = repository.getLocalDBFollowupFormList(country, identification, item.ws10, CONSTANTS.WRA_FOLLOWUP_TYPE)
                            form?.let {
                                item.wraTableDataExist = true
                                wra[index] = item
                            }
                        }
                    }
                    third.join()

                    val wraList = ArrayList<WraFollowup>(wra.sortedBy { it.ws11 })
                    ResponseStatusCallbacks.success(data = wraList, message = "Wra list found")
                } else
                    ResponseStatusCallbacks.error(data = null, message = "No wra found!")
            } catch (e: Exception) {
                _wraResponse.value =
                        ResponseStatusCallbacks.error(data = null, message = e.message.toString())
            }

        }

    }
}