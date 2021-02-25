package edu.aku.hassannaqvi.casi_register.ui.sections.followup.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.kennyc.view.MultiStateView
import edu.aku.hassannaqvi.casi_register.R
import edu.aku.hassannaqvi.casi_register.adapters.SelectedChildListAdapter
import edu.aku.hassannaqvi.casi_register.base.repository.GeneralRepository
import edu.aku.hassannaqvi.casi_register.base.repository.ResponseStatus
import edu.aku.hassannaqvi.casi_register.base.viewmodel.FollowupViewModel
import edu.aku.hassannaqvi.casi_register.core.MainApp
import edu.aku.hassannaqvi.casi_register.database.DatabaseHelper
import edu.aku.hassannaqvi.casi_register.models.ChildFollowup
import edu.aku.hassannaqvi.casi_register.models.Identification
import edu.aku.hassannaqvi.casi_register.ui.sections.followup.SelectedChildrenListActivity
import edu.aku.hassannaqvi.casi_register.utils.obtainViewModel
import edu.aku.hassannaqvi.casi_register.utils.openWarningFragment
import edu.aku.hassannaqvi.casi_register.utils.shared.SharedStorage
import kotlinx.android.synthetic.main.fragment_children_followup.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


class ChildrenFollowupFragment : Fragment(R.layout.fragment_children_followup) {

    lateinit var adapter: SelectedChildListAdapter
    lateinit var viewModel: FollowupViewModel
    val country: String by lazy {
        context?.let { SharedStorage.getCountryCode(it).toString() } ?: "0"
    }
    private val identification: Identification by lazy {
        Identification(MainApp.mainInfo.region_code, MainApp.mainInfo.district_code, MainApp.mainInfo.uc_code, MainApp.mainInfo.village_code)
    }
    lateinit var items: ArrayList<ChildFollowup>

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
        viewModel.childResponse.observe(viewLifecycleOwner, {
            it?.let {
                when (it.status) {
                    ResponseStatus.SUCCESS -> {
                        adapter.childItems = it.data as ArrayList<ChildFollowup>
                        items = adapter.childItems
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
                        adapter.childItems = items
                        multiStateView.viewState = MultiStateView.ViewState.CONTENT
                    }
                } else {
                    lifecycleScope.launch {
                        delay(1000)
                        val cropItem = items
                        adapter.childItems = cropItem.sortedBy { it.cs11 }.filter { it.cs11.startsWith(s.toString()) } as ArrayList<ChildFollowup>
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


    override fun onPause() {
        super.onPause()

        viewModel.getChildDataFromDB(country, identification)
    }
}