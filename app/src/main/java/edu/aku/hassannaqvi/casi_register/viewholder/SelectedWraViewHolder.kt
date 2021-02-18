package edu.aku.hassannaqvi.casi_register.viewholder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.aku.hassannaqvi.casi_register.R
import edu.aku.hassannaqvi.casi_register.databinding.ItemChildLayoutBinding
import edu.aku.hassannaqvi.casi_register.models.WraFollowup
import edu.aku.hassannaqvi.casi_register.utils.convertStringToUpperCase
import edu.aku.hassannaqvi.casi_register.utils.shortStringLength

/*
* @author Ali Azaz Alam
* */
class SelectedWraViewHolder(private val bi: ItemChildLayoutBinding) :
        RecyclerView.ViewHolder(bi.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: WraFollowup) {
        bi.childId.text = "ID-".plus(item.ws10)
        bi.resName.text = item.ws12.convertStringToUpperCase().shortStringLength()
        bi.name.text = item.ws11.convertStringToUpperCase().shortStringLength()
        val imageRes: Int = R.drawable.ic_mother
        bi.scrDate.text = item.ws08
        bi.icon2.setImageResource(R.drawable.ic_husband)
        val flagImage: Int
        if (item.wraTableDataExist) {
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
        fun create(viewGroup: ViewGroup): SelectedWraViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.item_child_layout, viewGroup, false)
            val binding = ItemChildLayoutBinding.bind(view)
            return SelectedWraViewHolder(binding)
        }
    }

    class WraViewDiffUtils(
            private val oldList: ArrayList<WraFollowup>,
            private val newList: ArrayList<WraFollowup>
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