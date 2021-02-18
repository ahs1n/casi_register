package edu.aku.hassannaqvi.casi_register.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import edu.aku.hassannaqvi.casi_register.models.ChildFollowup
import edu.aku.hassannaqvi.casi_register.models.WraFollowup
import edu.aku.hassannaqvi.casi_register.viewholder.SelectedChildViewHolder
import edu.aku.hassannaqvi.casi_register.viewholder.SelectedWraViewHolder
import kotlinx.android.synthetic.main.selected_child_view.view.*

/*
 * @author Ali Azaz Alam
 * */
class SelectedWraListAdapter(private val clickListener: OnItemClickListener) : RecyclerView.Adapter<SelectedWraViewHolder>() {

    var childItems: ArrayList<WraFollowup> = ArrayList()
        set(value) {
            field = value
            val diffCallback = SelectedWraViewHolder.WraViewDiffUtils(filteredWraItems, childItems)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            if (filteredWraItems.size > 0)
                filteredWraItems.clear()
            filteredWraItems.addAll(value)
            diffResult.dispatchUpdatesTo(this)
        }

    var filteredWraItems: ArrayList<WraFollowup> = ArrayList()
        set(value) {
            field = value
            val diffCallback = SelectedWraViewHolder.WraViewDiffUtils(filteredWraItems, childItems)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SelectedWraViewHolder {
        return SelectedWraViewHolder.create(viewGroup)
    }

    override fun onBindViewHolder(holder: SelectedWraViewHolder, i: Int) {
        val item = filteredWraItems[i]
        holder.bind(item)
        holder.itemView.parentLayout.setOnClickListener {
            clickListener.onItemClick(item, i)
        }
    }

    override fun getItemCount(): Int = filteredWraItems.size

    interface OnItemClickListener {
        fun onItemClick(item: WraFollowup, position: Int)
    }
}