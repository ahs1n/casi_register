package edu.aku.hassannaqvi.casi_register.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import edu.aku.hassannaqvi.casi_register.models.ChildFollowup
import edu.aku.hassannaqvi.casi_register.viewholder.SelectedChildViewHolder
import kotlinx.android.synthetic.main.selected_child_view.view.*

/*
 * @author Ali Azaz Alam
 * */
class SelectedChildListAdapter(private val clickListener: OnItemClickListener) : RecyclerView.Adapter<SelectedChildViewHolder>() {

    var childItems: ArrayList<ChildFollowup> = ArrayList()
        set(value) {
            field = value
            val diffCallback = SelectedChildViewHolder.ChildViewDiffUtils(filteredChildItems, childItems)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            if (filteredChildItems.size > 0)
                filteredChildItems.clear()
            filteredChildItems.addAll(value)
            diffResult.dispatchUpdatesTo(this)
        }

    var filteredChildItems: ArrayList<ChildFollowup> = ArrayList()
        set(value) {
            field = value
            val diffCallback = SelectedChildViewHolder.ChildViewDiffUtils(filteredChildItems, childItems)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SelectedChildViewHolder {
        return SelectedChildViewHolder.create(viewGroup)
    }

    override fun onBindViewHolder(holder: SelectedChildViewHolder, i: Int) {
        val item = filteredChildItems[i]
        holder.bind(item)
        holder.itemView.parentLayout.setOnClickListener {
            clickListener.onItemClick(item, i)
        }
    }

    override fun getItemCount(): Int = filteredChildItems.size

    interface OnItemClickListener {
        fun onItemClick(item: ChildFollowup, position: Int)
    }
}