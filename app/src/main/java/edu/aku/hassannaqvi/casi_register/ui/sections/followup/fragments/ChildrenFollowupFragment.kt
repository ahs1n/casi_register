package edu.aku.hassannaqvi.casi_register.ui.sections.followup.fragments

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kennyc.view.MultiStateView
import edu.aku.hassannaqvi.casi_register.R
import edu.aku.hassannaqvi.casi_register.adapters.SelectedChildListAdapter
import edu.aku.hassannaqvi.casi_register.base.repository.GeneralRepository
import edu.aku.hassannaqvi.casi_register.base.repository.ResponseStatus
import edu.aku.hassannaqvi.casi_register.base.repository.ResponseStatusCallbacks
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
import kotlin.collections.ArrayList


class ChildrenFollowupFragment : Fragment(R.layout.fragment_children_followup),
    View.OnClickListener {

    lateinit var adapter: SelectedChildListAdapter
    lateinit var viewModel: FollowupViewModel

    private var minimumSearchLength: Int = 3
    private var loadItemsCount: Int = 10

    val country: String by lazy {
        context?.let { SharedStorage.getCountryCode(it).toString() } ?: "0"
    }
    private val identification: Identification by lazy {
        Identification(
            MainApp.mainInfo.region_code,
            MainApp.mainInfo.district_code,
            MainApp.mainInfo.uc_code,
            MainApp.mainInfo.village_code
        )
    }
    var items: List<ChildFollowup> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = obtainViewModel(
            activity as SelectedChildrenListActivity,
            FollowupViewModel::class.java,
            GeneralRepository(DatabaseHelper(activity))
        )
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private var isViewCreated: Boolean = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
        callingRecyclerView()

        viewModel.getChildDataFromDB(country, identification)

        initList()

        // On keyboard done click
        txtFilter.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Call onDone result here
                searchList()
                true
            }
            false
        }
        searchIV.setOnClickListener(this)
        resetSearchIV.setOnClickListener(this)
        loadMoreBtn.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        if (isViewCreated) {
            isViewCreated = false
            return
        }
        if (MainApp.IS_RECORD_SAVE) {
            MainApp.IS_RECORD_SAVE = false
            adapter.childItems = arrayListOf()
            adapter.filteredChildItems = arrayListOf()

            viewModel.getChildDataFromDB(country, identification)
//            initList()
        }
    }

    /*
    * Initialize recyclerView with onClickListener
    * */
    private fun callingRecyclerView() {
        adapter = SelectedChildListAdapter(object : SelectedChildListAdapter.OnItemClickListener {
            override fun onItemClick(item: ChildFollowup, position: Int) {
                openWarningFragment(
                    id = 1,
                    title = getString(R.string.confirmation),
                    message = resources.getString(R.string.cntBtn)
                        .replace("-", item.cs11.toUpperCase(Locale.ENGLISH)),
                    item = item
                )
            }
        })
        childList.adapter = adapter
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.searchIV -> searchList()
            R.id.resetSearchIV -> {
                hideKeyboard()
                txtFilter.text = null
                adapter.filteredChildItems.clear()
                initList()
                resetSearchIV.visibility = View.INVISIBLE
            }
            R.id.loadMoreBtn -> loadMore(
                adapter.filteredChildItems.size,
                adapter.filteredChildItems.size + (loadItemsCount - 1)
            )
        }
    }

    // Initialize list
    private fun initList() {
        multiStateView.viewState = MultiStateView.ViewState.EMPTY
        viewModel.childResponse.observe(viewLifecycleOwner, {
            it?.let {
                when (it.status) {
                    ResponseStatus.SUCCESS -> {
                        it.data?.let { item ->
                            val rootItem = arrayListOf<ChildFollowup>()
                            item.forEach { root ->
                                root.cs11 = root.cs11.toLowerCase(Locale.ENGLISH)
                                rootItem.add(root)
                            }
                            items = rootItem
//                            val count: Int = ((it.data as ArrayList<ChildFollowup>).chunked(20)).count()
//                            Log.e("CFF", count.toString());
//                            listItems = it.data as ArrayList<ChildFollowup>
                            adapter.childItems =
                                items.slice(0 until loadItemsCount) as ArrayList<ChildFollowup>
//                            adapter.childItems = it.data as ArrayList<ChildFollowup>
                            multiStateView.viewState = MultiStateView.ViewState.CONTENT
                            showLoadMoreBtn()
//                            loadMoreBtn.visibility = View.VISIBLE
                        } ?: run {
                            multiStateView.viewState = MultiStateView.ViewState.EMPTY
                            loadMoreBtn.visibility = View.GONE
                        }
                    }
                    ResponseStatus.ERROR -> {
                        multiStateView.viewState = MultiStateView.ViewState.EMPTY
                        loadMoreBtn.visibility = View.GONE
                    }
                    ResponseStatus.LOADING -> {
                        multiStateView.viewState = MultiStateView.ViewState.LOADING
                        loadMoreBtn.visibility = View.GONE
                    }
                }
            }
        })
    }

    // Search/Filter list
    private fun searchList() {
        hideKeyboard()
        val s: String = txtFilter.text.toString()
        if (s.isEmpty()) {
            Toast.makeText(activity, getString(R.string.enter_search_text_error), Toast.LENGTH_LONG)
                .show();
            return
        }

        if (s.length < 3) {
            Toast.makeText(
                activity,
                String.format(getString(R.string.min_length_error), minimumSearchLength),
                Toast.LENGTH_LONG
            ).show()
            return
        }
        loadMoreBtn.visibility = View.GONE
        multiStateView.viewState = MultiStateView.ViewState.LOADING
        adapter.filteredChildItems.clear()
        lifecycleScope.launch {
            delay(1000)
            val cropItem = items
            adapter.filteredChildItems = cropItem.sortedBy { it.cs11 }.filter {
                it.cs11.contains(
                    s.toString().toLowerCase(Locale.ENGLISH)
                )
            } as ArrayList<ChildFollowup>
            if (adapter.filteredChildItems.size > 0) {
                multiStateView.viewState = MultiStateView.ViewState.CONTENT
            } else {
                multiStateView.viewState = MultiStateView.ViewState.EMPTY
            }
            loadMoreBtn.visibility = View.GONE
            adapter.notifyDataSetChanged()
            resetSearchIV.visibility = View.VISIBLE
        }
    }

    // Load more items
    private fun loadMore(start: Int, count: Int) {
        adapter.filteredChildItems.addAll(items.slice(start..count))
        adapter.notifyItemRangeChanged(
            adapter.filteredChildItems.size,
            adapter.filteredChildItems.size
        )
    }

    // Show load more button - Delay is intentional
    private fun showLoadMoreBtn() {
        Handler().postDelayed({
            loadMoreBtn.visibility = View.VISIBLE
        }, 1000)
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    // Hide keyboard
    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}