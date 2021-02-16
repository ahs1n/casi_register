package edu.aku.hassannaqvi.casi_register.viewholder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.aku.hassannaqvi.casi_register.databinding.SelectedChildViewBinding
import edu.aku.hassannaqvi.casi_register.R
import edu.aku.hassannaqvi.casi_register.models.ChildFollowup
import edu.aku.hassannaqvi.casi_register.utils.convertStringToUpperCase
import edu.aku.hassannaqvi.casi_register.utils.shortStringLength

/*
* @author Ali Azaz Alam dt. 01.14.21
* */
class SelectedChildViewHolder(private val bi: SelectedChildViewBinding) :
        RecyclerView.ViewHolder(bi.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: ChildFollowup) {
        bi.childId.text = "ID-".plus(item.cs10)
        bi.resName.text = item.cs11a.convertStringToUpperCase().shortStringLength()
        bi.name.text = item.cs11.convertStringToUpperCase().shortStringLength()
//        bi.age.text = String.format("Age: [%d]M", item.cs11.toInt().times(12).plus(item.cb0502.toInt()))
        val imageRes: Int = if (item.cs13 == "1") R.drawable.ctr_childboy else R.drawable.ctr_childgirl
        val flagImage: Int
        if (item.childTableDataExist) {
            bi.parentLayout.isEnabled = false
            flagImage = R.drawable.ic_complete_star
        } else {
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
                    .inflate(R.layout.selected_child_view, viewGroup, false)
            val binding = SelectedChildViewBinding.bind(view)
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