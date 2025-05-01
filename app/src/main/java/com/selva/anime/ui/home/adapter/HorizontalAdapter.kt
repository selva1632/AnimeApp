package com.selva.anime.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selva.anime.databinding.AnimeCategoryItemViewHolderBinding
import com.selva.anime.presentation.data.HorizontalItem
import com.selva.anime.ui.home.contract.UiEvent
import com.selva.anime.ui.home.adapter.ext.GenericAnimeDiffUtil

class HorizontalAdapter(
    private var item: List<HorizontalItem.NestedItem>,
    private val handleEvent: (UiEvent) -> Unit
) : RecyclerView.Adapter<HorizontalViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(data: List<HorizontalItem.NestedItem>) {
        val diffUtilCallback = GenericAnimeDiffUtil(
            oldList = item,
            newList = data,
            areItemTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
            areContentTheSame = { oldItem, newItem -> oldItem == newItem }
        )

        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        item = data
        diffResult.dispatchUpdatesTo(this@HorizontalAdapter)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        return HorizontalViewHolder(
            AnimeCategoryItemViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                null,
                false
            ),
            handleEvent
        )
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        holder.bind(item[position])
        holder.binding.executePendingBindings()
    }

    override fun onViewRecycled(holder: HorizontalViewHolder) {
        super.onViewRecycled(holder)
        Glide.with(holder.itemView.context).clear(holder.itemView)
    }
}