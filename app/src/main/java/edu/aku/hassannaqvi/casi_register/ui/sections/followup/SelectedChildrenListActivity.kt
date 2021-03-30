package edu.aku.hassannaqvi.casi_register.ui.sections.followup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import edu.aku.hassannaqvi.casi_register.CONSTANTS
import edu.aku.hassannaqvi.casi_register.CONSTANTS.Companion.FOLLOWUP_FLAG
import edu.aku.hassannaqvi.casi_register.R
import edu.aku.hassannaqvi.casi_register.adapters.SelectedChildListAdapter
import edu.aku.hassannaqvi.casi_register.databinding.ActivityFollowupBinding
import edu.aku.hassannaqvi.casi_register.models.ChildFollowup
import edu.aku.hassannaqvi.casi_register.models.WraFollowup
import edu.aku.hassannaqvi.casi_register.ui.sections.Section02CSFPActivity
import edu.aku.hassannaqvi.casi_register.ui.sections.Section04WSFPActivity
import edu.aku.hassannaqvi.casi_register.ui.sections.followup.fragments.ChildrenFollowupFragment
import edu.aku.hassannaqvi.casi_register.ui.sections.followup.fragments.WraFollowupFragment
import edu.aku.hassannaqvi.casi_register.utils.WarningActivityInterface
import edu.aku.hassannaqvi.casi_register.utils.gotoActivityWithSerializable
import java.util.*

class SelectedChildrenListActivity : AppCompatActivity(), WarningActivityInterface {

    lateinit var adapter: SelectedChildListAdapter
    lateinit var bi: ActivityFollowupBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = DataBindingUtil.setContentView(this, R.layout.activity_followup)

        /*
        * Get followup flag whether it's child or wra
        * */
        val followupFlag = intent.getBooleanExtra(FOLLOWUP_FLAG, true)

        /*
        * Nested Toolbar
        * */
        bi.toolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.black))
        bi.toolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.black))
        bi.toolbarLayout.title = if (followupFlag) getString(R.string.childFollowup) else getString(R.string.wraFollowup)


        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                if (followupFlag) replace<ChildrenFollowupFragment>(R.id.fragment_container)
                else replace<WraFollowupFragment>(R.id.fragment_container)
            }
        }

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
            2 -> {
                val information = item as WraFollowup
                gotoActivityWithSerializable(Section04WSFPActivity::class.java, CONSTANTS.ITEM_DATA, information)
            }
        }
    }

}