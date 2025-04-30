package com.selva.anime.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selva.anime.databinding.NestedItemViewHolderBinding
import com.selva.anime.databinding.SlidingImageViewHolderBinding
import com.selva.anime.presentation.data.VerticalItem
import com.selva.anime.ui.home.contract.UiEvent
import com.selva.anime.ui.home.adapter.ext.GenericAnimeDiffUtil

class VerticalAdapter(
    private val handleEvent: (UiEvent) -> Unit
) : RecyclerView.Adapter<VerticalViewHolder>() {

    private val recycledViewPool = RecyclerView.RecycledViewPool()
    private var _animeItems = listOf<VerticalItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(animeItems: List<VerticalItem>) {
        val diffUtilCallback = GenericAnimeDiffUtil<VerticalItem>(
            oldList = _animeItems,
            newList = animeItems,
            areItemTheSame = { oldItem, newItem ->
                when {
                    oldItem is VerticalItem.SliderItem && newItem is VerticalItem.SliderItem -> true
                    oldItem is VerticalItem.SuggestionItem && newItem is VerticalItem.SuggestionItem -> oldItem.id == newItem.id
                    else -> false
                }
            },
            areContentTheSame = { oldItem, newItem ->
                oldItem == newItem
            }
        )

        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        _animeItems = animeItems
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        return when (viewType) {
            TYPE_SLIDER -> VerticalViewHolder.SlidingViewHolder(
                SlidingImageViewHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    null,
                    false
                ),
                handleEvent
            )

            TYPE_NESTED -> VerticalViewHolder.NestedViewHolder(
                NestedItemViewHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    null,
                    false
                ),
                handleEvent
            )

            else -> {
                throw IllegalArgumentException("unknown viewType - $viewType")
            }
        }
    }

    override fun getItemCount(): Int {
        return _animeItems.size
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        holder.bind(_animeItems[position], recycledViewPool)
        holder.binding.executePendingBindings()
    }

    override fun onViewRecycled(holder: VerticalViewHolder) {
        super.onViewRecycled(holder)
        Glide.with(holder.itemView.context).clear(holder.itemView)
    }

    override fun getItemViewType(position: Int): Int {
        return when(_animeItems[position]) {
            is VerticalItem.SuggestionItem -> TYPE_NESTED
            is VerticalItem.SliderItem -> TYPE_SLIDER
        }
    }

    companion object {
        private const val TYPE_SLIDER = 1
        private const val TYPE_NESTED = 2
    }
}