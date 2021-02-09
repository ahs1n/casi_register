package edu.aku.hassannaqvi.casi_register.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.aku.hassannaqvi.casi_register.base.repository.GeneralRepository
import edu.aku.hassannaqvi.casi_register.base.repository.ResponseStatusCallbacks
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SelectedChildrenListViewModel(internal val repository: GeneralRepository) : ViewModel() {

/*    private val _childResponse: MutableLiveData<ResponseStatusCallbacks<List<ChildInformation>>> = MutableLiveData()

    val childResponse: MutableLiveData<ResponseStatusCallbacks<List<ChildInformation>>>
        get() = _childResponse


    fun getChildDataFromDB(cluster: String, hhno: String, uuid: String) {
        _childResponse.value = ResponseStatusCallbacks.loading(null)
        viewModelScope.launch {
            try {
                delay(1000)
                val children = repository.getSelectedChildList(cluster, hhno, uuid)
                _childResponse.value = if (children.size > 0) {
                    val childList = ArrayList<ChildInformation>(children.sortedBy { it.cb07 })
                    ResponseStatusCallbacks.success(data = childList, message = "Child list found")
                } else
                    ResponseStatusCallbacks.error(data = null, message = "No child found!")
            } catch (e: Exception) {
                _childResponse.value =
                        ResponseStatusCallbacks.error(data = null, message = e.message.toString())
            }

        }

    }*/
}