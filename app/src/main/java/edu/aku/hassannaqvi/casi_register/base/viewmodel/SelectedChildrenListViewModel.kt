package edu.aku.hassannaqvi.casi_register.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import edu.aku.hassannaqvi.casi_register.CONSTANTS
import edu.aku.hassannaqvi.casi_register.base.repository.GeneralRepository
import edu.aku.hassannaqvi.casi_register.base.repository.ResponseStatusCallbacks
import edu.aku.hassannaqvi.casi_register.models.ChildFollowup
import edu.aku.hassannaqvi.casi_register.models.Identification
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SelectedChildrenListViewModel(internal val repository: GeneralRepository) : ViewModel() {

    private val _childResponse: MutableLiveData<ResponseStatusCallbacks<List<ChildFollowup>>> = MutableLiveData()

    val childResponse: MutableLiveData<ResponseStatusCallbacks<List<ChildFollowup>>>
        get() = _childResponse


    fun getChildDataFromDB(country: String, identification: Identification) {
        _childResponse.value = ResponseStatusCallbacks.loading(null)
        viewModelScope.launch {
            try {
                delay(1000)
                val children: ArrayList<ChildFollowup> = ArrayList()

                val first = async { children.addAll(repository.getSelectedServerChildList(country, identification)) }
                val second = async { children.addAll(repository.getSelectedChildLocalFormList(country, identification)) }

                first.await()
                second.await()

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

                    val childList = ArrayList<ChildFollowup>(children.sortedBy { it.cs11 })
                    ResponseStatusCallbacks.success(data = childList, message = "Child list found")
                } else
                    ResponseStatusCallbacks.error(data = null, message = "No child found!")
            } catch (e: Exception) {
                _childResponse.value =
                        ResponseStatusCallbacks.error(data = null, message = e.message.toString())
            }

        }

    }
}