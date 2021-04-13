package edu.aku.hassannaqvi.casi_register.ui.sections.followup.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.kennyc.view.MultiStateView
import edu.aku.hassannaqvi.casi_register.R
import edu.aku.hassannaqvi.casi_register.adapters.SelectedWraListAdapter
import edu.aku.hassannaqvi.casi_register.base.repository.GeneralRepository
import edu.aku.hassannaqvi.casi_register.base.repository.ResponseStatus
import edu.aku.hassannaqvi.casi_register.base.viewmodel.FollowupViewModel
import edu.aku.hassannaqvi.casi_register.core.MainApp
import edu.aku.hassannaqvi.casi_register.database.DatabaseHelper
import edu.aku.hassannaqvi.casi_register.models.Identification
import edu.aku.hassannaqvi.casi_register.models.WraFollowup
import edu.aku.hassannaqvi.casi_register.ui.sections.followup.SelectedChildrenListActivity
import edu.aku.hassannaqvi.casi_register.utils.obtainViewModel
import edu.aku.hassannaqvi.casi_register.utils.openWarningFragment
import edu.aku.hassannaqvi.casi_register.utils.shared.SharedStorage
import kotlinx.android.synthetic.main.fragment_children_followup.*
import kotlinx.android.synthetic.main.fragment_children_followup.multiStateView
import kotlinx.android.synthetic.main.fragment_wra_followup.*
import kotlinx.android.synthetic.main.fragment_wra_followup.txtFilter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


class WraFollowupFragment : Fragment(R.layout.fragment_wra_followup) {

    lateinit var adapter: SelectedWraListAdapter
    lateinit var viewModel: FollowupViewModel
    val country: String by lazy {
        context?.let { SharedStorage.getCountryCode(it).toString() } ?: "0"
    }
    private val identification: Identification by lazy {
        Identification(MainApp.mainInfo.region_code, MainApp.mainInfo.district_code, MainApp.mainInfo.uc_code, MainApp.mainInfo.village_code)
    }
    var items: List<WraFollowup> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = obtainViewModel(activity as SelectedChildrenListActivity, FollowupViewModel::class.java, GeneralRepository(DatabaseHelper(activity)))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callingRecyclerView()

        viewModel.getChildDataFromDB(country, identification)

        /*
       * Fetch child list
       * */
        multiStateView.viewState = MultiStateView.ViewState.EMPTY
        viewModel.wraResponse.observe(viewLifecycleOwner, {
            it?.let {
                when (it.status) {
                    ResponseStatus.SUCCESS -> {
                        it.data?.let { item ->
                            val rootItem = arrayListOf<WraFollowup>()
                            item.forEach { root ->
                                root.ws11 = root.ws11.toLowerCase(Locale.ENGLISH)
                                rootItem.add(root)
                            }
                            items = rootItem
                            adapter.childItems = it.data as ArrayList<WraFollowup>
                            multiStateView.viewState = MultiStateView.ViewState.CONTENT
                        } ?: run {
                            multiStateView.viewState = MultiStateView.ViewState.EMPTY
                        }
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

        /*
        * Filter listener
        * */

        txtFilter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                multiStateView.viewState = MultiStateView.ViewState.LOADING
            }

            override fun afterTextChanged(s: Editable?) {
                if (s == null) {
                    lifecycleScope.launch {
                        delay(1000)
                        adapter.childItems = items as ArrayList<WraFollowup>
                        multiStateView.viewState = MultiStateView.ViewState.CONTENT
                    }
                } else {
                    lifecycleScope.launch {
                        delay(1000)
                        val cropItem = items
                        adapter.childItems = cropItem.sortedBy { it.ws11 }.filter { it.ws11.contains(s.toString().toLowerCase(Locale.ENGLISH)) } as ArrayList<WraFollowup>
                        if (adapter.childItems.size > 0)
                            multiStateView.viewState = MultiStateView.ViewState.CONTENT
                        else
                            multiStateView.viewState = MultiStateView.ViewState.EMPTY
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
                        title = getString(R.string.confirmation),
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