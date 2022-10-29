package edu.aku.hassannaqvi.casi_register.viewholder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.aku.hassannaqvi.casi_register.R
import edu.aku.hassannaqvi.casi_register.core.MainApp
import edu.aku.hassannaqvi.casi_register.databinding.ItemChildLayoutBinding
import edu.aku.hassannaqvi.casi_register.models.ChildFollowup
import edu.aku.hassannaqvi.casi_register.utils.convertStringToUpperCase
import edu.aku.hassannaqvi.casi_register.utils.shortStringLength
import org.apache.commons.lang3.StringUtils

/*
* @author Ali Azaz Alam
* */
class SelectedChildViewHolder(private val bi: ItemChildLayoutBinding) :
        RecyclerView.ViewHolder(bi.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: ChildFollowup) {
        bi.childId.text = "ID-".plus(item.cs10)
        bi.resName.text = item.cs11a.convertStringToUpperCase().shortStringLength()
        bi.name.text = item.cs11.convertStringToUpperCase().shortStringLength()
        val imageRes: Int = if (item.cs13 == "1") R.drawable.ic_boy else R.drawable.ic_girl
        bi.scrDate.text = item.cs08
        bi.fRound.text = item.fupNo
        val db = MainApp.appInfo.dbHelper
        bi.fDate.text = db.getChildLastFollowup(item.cs10) ?: "No follow-up done"

        val flagImage: Int

        if (item.fupDt == StringUtils.EMPTY) bi.parentLayout.isEnabled =
            false else bi.followupDT.text = item.fupDt
        if (item.childTableDataExist) {
//            bi.parentLayout.isEnabled = false
            bi.parentLayout.isEnabled = true
            flagImage = R.drawable.ic_complete_star
        } else {
//            bi.parentLayout.isEnabled = true
            flagImage = R.drawable.ic_incomplete_star
        }

        Glide.with(this.itemView.context)
            .asBitmap()
            .load(imageRes)
            .into(bi.childImg)
        Glide.with(this.itemView.context)
            .asBitmap()
            .load(flagImage)
            .into(bi.completeFlag)
        bi.executePendingBindings()
    }


    companion object {
        fun create(viewGroup: ViewGroup): SelectedChildViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.item_child_layout, viewGroup, false)
            val binding = ItemChildLayoutBinding.bind(view)
            return SelectedChildViewHolder(binding)
        }
    }

    class ChildViewDiffUtils(
            private val oldList: ArrayList<ChildFollowup>,
            private val newList: ArrayList<ChildFollowup>
    ) :
            DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}