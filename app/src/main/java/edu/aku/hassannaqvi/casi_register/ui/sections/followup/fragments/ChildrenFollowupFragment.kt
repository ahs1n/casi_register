package edu.aku.hassannaqvi.casi_register.ui.sections.followup.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennyc.view.MultiStateView
import edu.aku.hassannaqvi.casi_register.R
import edu.aku.hassannaqvi.casi_register.adapters.SelectedChildListAdapter
import edu.aku.hassannaqvi.casi_register.base.repository.GeneralRepository
import edu.aku.hassannaqvi.casi_register.base.repository.ResponseStatus
import edu.aku.hassannaqvi.casi_register.base.viewmodel.SelectedChildrenListViewModel
import edu.aku.hassannaqvi.casi_register.core.MainApp
import edu.aku.hassannaqvi.casi_register.database.DatabaseHelper
import edu.aku.hassannaqvi.casi_register.models.ChildFollowup
import edu.aku.hassannaqvi.casi_register.models.Identification
import edu.aku.hassannaqvi.casi_register.ui.sections.followup.SelectedChildrenListActivity
import edu.aku.hassannaqvi.casi_register.utils.*
import edu.aku.hassannaqvi.casi_register.utils.shared.SharedStorage
import kotlinx.android.synthetic.main.fragment_children_followup.*
import java.util.*


class ChildrenFollowupFragment : Fragment(R.layout.fragment_children_followup) {

    lateinit var adapter: SelectedChildListAdapter
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
        viewModel.childResponse.observe(viewLifecycleOwner, {
            it?.let {
                when (it.status) {
                    ResponseStatus.SUCCESS -> {
                        adapter.childItems = it.data as ArrayList<ChildFollowup>
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
        adapter = SelectedChildListAdapter(object : SelectedChildListAdapter.OnItemClickListener {
            override fun onItemClick(item: ChildFollowup, position: Int) {
                openWarningFragment(
                        id = 1,
                        title = "CONFIRMATION!",
                        message = "Are you sure, you want to continue ${item.cs11.toUpperCase(Locale.ENGLISH)} interview?",
                        item = item)
            }
        })
        childList.adapter = adapter
    }


    /*
    * Get childList on resume event
    * */
    override fun onResume() {
        super.onResume()

        val villageItem = MainApp.mainInfo
        val country = context?.let { SharedStorage.getCountryCode(it).toString() } ?: "0"
        viewModel.getChildDataFromDB(country, Identification(villageItem.region_code, villageItem.district_code, villageItem.uc_code, villageItem.village_code))
    }
}