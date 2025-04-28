package com.selva.anime.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selva.anime.databinding.NestedItemViewHolderBinding
import com.selva.anime.databinding.SlidingImageViewHolderBinding
import com.selva.anime.presentation.data.VerticalItem
import com.selva.anime.ui.home.eventlistener.AnimeClickListener

class VerticalAdapter(
    private val eventListener: AnimeClickListener
) : RecyclerView.Adapter<VerticalViewHolder>() {
    private val recycledViewPool = RecyclerView.RecycledViewPool()
    private val _animeItems = mutableListOf<VerticalItem>()

    // TODO diff utils
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(animeItems: List<VerticalItem>) {
        _animeItems.clear()
        _animeItems.addAll(animeItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        return when (viewType) {
            TYPE_SLIDER -> VerticalViewHolder.SlidingViewHolder(
                SlidingImageViewHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    null,
                    false
                ),
                eventListener
            )

            TYPE_NESTED -> VerticalViewHolder.NestedViewHolder(
                NestedItemViewHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    null,
                    false
                ),
                eventListener
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
        holder.bind(_animeItems[position], eventListener, recycledViewPool)
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