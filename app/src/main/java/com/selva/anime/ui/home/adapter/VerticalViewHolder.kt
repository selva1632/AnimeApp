package com.selva.anime.ui.home.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.selva.anime.databinding.NestedItemViewHolderBinding
import com.selva.anime.databinding.SlidingImageViewHolderBinding
import com.selva.anime.domain.model.AnimeSectionItem
import com.selva.anime.ui.home.eventlistener.AnimeClickListener
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations

sealed class VerticalViewHolder(
    open val binding: ViewDataBinding,
    open val eventListener: AnimeClickListener
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(
        item: AnimeSectionItem,
        listener: AnimeClickListener,
        recycledViewPool: RecycledViewPool
    )

    class NestedViewHolder(
        override val binding: NestedItemViewHolderBinding,
        override val eventListener: AnimeClickListener
    ) : VerticalViewHolder(binding, eventListener) {

        private val horizontalAdapter by lazy { HorizontalAdapter(emptyList(), eventListener) }

        init {
            with(binding.horizontalRecyclerView) {
                adapter = horizontalAdapter
                layoutManager = LinearLayoutManager(
                    context, RecyclerView.HORIZONTAL, false
                )
                setHasFixedSize(true)
            }
        }

        override fun bind(
            item: AnimeSectionItem,
            listener: AnimeClickListener,
            recycledViewPool: RecycledViewPool
        ) {
            with(binding) {
                bindingItem = item
            }
        }
    }

    class SlidingViewHolder(
        override val binding: SlidingImageViewHolderBinding,
        override val eventListener: AnimeClickListener
    ) : VerticalViewHolder(binding, eventListener) {

        private val adapter by lazy { SliderAdapter() }

        init {
            setUpSlider()
        }

        private fun setUpSlider() = with(binding.imageSlider) {
            setSliderAdapter(adapter)
            setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            setIndicatorAnimation(IndicatorAnimationType.SLIDE)
            setSliderAnimationDuration(1000)
            scrollTimeInSec = 2
            startAutoCycle()
        }

        override fun bind(
            item: AnimeSectionItem,
            listener: AnimeClickListener,
            recycledViewPool: RecycledViewPool
        ) {
            adapter.submitList(item.data)

            if (item.data.isEmpty()) {
                binding.imageSlider.stopAutoCycle()
            } else {
                binding.imageSlider.startAutoCycle()
            }
        }
    }
}