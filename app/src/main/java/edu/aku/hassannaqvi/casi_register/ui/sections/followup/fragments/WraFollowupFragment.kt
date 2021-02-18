package edu.aku.hassannaqvi.casi_register.ui.sections.followup.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennyc.view.MultiStateView
import edu.aku.hassannaqvi.casi_register.R
import edu.aku.hassannaqvi.casi_register.adapters.SelectedWraListAdapter
import edu.aku.hassannaqvi.casi_register.base.repository.GeneralRepository
import edu.aku.hassannaqvi.casi_register.base.repository.ResponseStatus
import edu.aku.hassannaqvi.casi_register.base.viewmodel.SelectedChildrenListViewModel
import edu.aku.hassannaqvi.casi_register.core.MainApp
import edu.aku.hassannaqvi.casi_register.database.DatabaseHelper
import edu.aku.hassannaqvi.casi_register.models.Identification
import edu.aku.hassannaqvi.casi_register.models.WraFollowup
import edu.aku.hassannaqvi.casi_register.ui.sections.followup.SelectedChildrenListActivity
import edu.aku.hassannaqvi.casi_register.utils.*
import edu.aku.hassannaqvi.casi_register.utils.shared.SharedStorage
import kotlinx.android.synthetic.main.fragment_children_followup.multiStateView
import kotlinx.android.synthetic.main.fragment_wra_followup.*
import java.util.*


class WraFollowupFragment : Fragment(R.layout.fragment_wra_followup) {

    lateinit var adapter: SelectedWraListAdapter
    lateinit var viewModel: SelectedChildrenListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = obtainViewModel(activity as SelectedChildrenListActivity, SelectedChildrenListViewModel::class.java, GeneralRepository(DatabaseHelper(activity)))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callingRecyclerView()

        /*
       * Fetch child list
       * */
        multiStateView.viewState = MultiStateView.ViewState.EMPTY
        viewModel.wraResponse.observe(viewLifecycleOwner, {
            it?.let {
                when (it.status) {
                    ResponseStatus.SUCCESS -> {
                        adapter.childItems = it.data as ArrayList<WraFollowup>
                        multiStateView.viewState = MultiStateView.ViewState.CONTENT
                    }
                    ResponseStatus.ERROR -> {
                        multiStateView.viewState = MultiStateView.ViewState.EMPTY
                    }
                    ResponseStatus.LOADING -> {
                        multiStateView.viewState = MultiStateView.ViewState.LOADING
                    }
                }
            }
        })


    }


    /*
    * Initialize recyclerView with onClickListener
    * */
    private fun callingRecyclerView() {
        adapter = SelectedWraListAdapter(object : SelectedWraListAdapter.OnItemClickListener {
            override fun onItemClick(item: WraFollowup, position: Int) {
                openWarningFragment(
                        id = 2,
                        title = "CONFIRMATION!",
                        message = "Are you sure, you want to continue ${item.ws11.toUpperCase(Locale.ENGLISH)} interview?",
                        item = item)
            }
        })
        wraList.adapter = adapter
    }


    /*
    * Get childList on resume event
    * */
    override fun onResume() {
        super.onResume()

        val villageItem = MainApp.mainInfo
        val country = context?.let { SharedStorage.getCountryCode(it).toString() } ?: "0"
        viewModel.getWraDataFromDB(country, Identification(villageItem.region_code, villageItem.district_code, villageItem.uc_code, villageItem.village_code))
    }
}