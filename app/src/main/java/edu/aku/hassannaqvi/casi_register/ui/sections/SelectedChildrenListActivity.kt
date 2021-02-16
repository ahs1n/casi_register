package edu.aku.hassannaqvi.casi_register.ui.sections

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.kennyc.view.MultiStateView
import edu.aku.hassannaqvi.casi_register.CONSTANTS
import edu.aku.hassannaqvi.casi_register.R
import edu.aku.hassannaqvi.casi_register.adapters.SelectedChildListAdapter
import edu.aku.hassannaqvi.casi_register.base.repository.GeneralRepository
import edu.aku.hassannaqvi.casi_register.base.repository.ResponseStatus
import edu.aku.hassannaqvi.casi_register.base.viewmodel.SelectedChildrenListViewModel
import edu.aku.hassannaqvi.casi_register.core.MainApp
import edu.aku.hassannaqvi.casi_register.database.DatabaseHelper
import edu.aku.hassannaqvi.casi_register.databinding.ActivitySelectedChildrenListBinding
import edu.aku.hassannaqvi.casi_register.models.ChildFollowup
import edu.aku.hassannaqvi.casi_register.models.Identification
import edu.aku.hassannaqvi.casi_register.utils.WarningActivityInterface
import edu.aku.hassannaqvi.casi_register.utils.gotoActivityWithSerializable
import edu.aku.hassannaqvi.casi_register.utils.obtainViewModel
import edu.aku.hassannaqvi.casi_register.utils.openWarningActivity
import edu.aku.hassannaqvi.casi_register.utils.shared.SharedStorage
import java.util.*

class SelectedChildrenListActivity : AppCompatActivity(), WarningActivityInterface {

    lateinit var adapter: SelectedChildListAdapter
    lateinit var bi: ActivitySelectedChildrenListBinding

    /*
    * Using old approach to visible viewmodel in all sub classes
    * Serial no increment on every new entry
    * */
    companion object {
        lateinit var viewModel: SelectedChildrenListViewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = DataBindingUtil.setContentView(this, R.layout.activity_selected_children_list)
        viewModel = obtainViewModel(SelectedChildrenListViewModel::class.java, GeneralRepository(DatabaseHelper(this)))
        callingRecyclerView()


        /*
        * Nested Toolbar
        * */
        bi.toolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.black))
        bi.toolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.black))


        /*
        * Fetch child list
        * */
        bi.multiStateView.viewState = MultiStateView.ViewState.EMPTY
        viewModel.childResponse.observe(this, {
            it?.let {
                when (it.status) {
                    ResponseStatus.SUCCESS -> {
                        adapter.childItems = it.data as ArrayList<ChildFollowup>
                        bi.multiStateView.viewState = MultiStateView.ViewState.CONTENT
                    }
                    ResponseStatus.ERROR -> {
                        bi.multiStateView.viewState = MultiStateView.ViewState.EMPTY
                    }
                    ResponseStatus.LOADING -> {
                        bi.multiStateView.viewState = MultiStateView.ViewState.LOADING
                    }
                }
            }
        })

    }


    /*
    * Callback call after pressing Child item in recyclerview
    * */
    override fun callWarningActivity(id: Int, item: Any?) {
        when (id) {
            1 -> {
                val information = item as ChildFollowup
                gotoActivityWithSerializable(Section02CSFPActivity::class.java, CONSTANTS.ITEM_DATA, information)
            }
        }
    }


    /*
    * Initialize recyclerView with onClickListener
    * */
    private fun callingRecyclerView() {
        adapter = SelectedChildListAdapter(object : SelectedChildListAdapter.OnItemClickListener {
            override fun onItemClick(item: ChildFollowup, position: Int) {
                openWarningActivity(
                        id = 1,
                        title = "CONFIRMATION!",
                        message = "Are you sure, you want to continue ${item.cs11.toUpperCase(Locale.ENGLISH)} interview?",
                        item = item)
            }
        })
        bi.childList.adapter = adapter
    }


    /*
    * Get childList on resume event
    * */
    override fun onResume() {
        super.onResume()

        val villageItem = MainApp.mainInfo
        viewModel.getChildDataFromDB(SharedStorage.getCountryCode(this).toString(), Identification(villageItem.region_code, villageItem.district_code, villageItem.uc_code, villageItem.village_code))
    }
}