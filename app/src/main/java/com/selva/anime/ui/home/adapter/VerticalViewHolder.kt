package com.selva.anime.ui.home.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.selva.anime.databinding.NestedItemViewHolderBinding
import com.selva.anime.databinding.SlidingImageViewHolderBinding
import com.selva.anime.presentation.data.VerticalItem
import com.selva.anime.ui.home.contract.UiEvent
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations

sealed class VerticalViewHolder(
    open val binding: ViewDataBinding,
    open val handleEvent: (UiEvent) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(
        item: VerticalItem,
        recycledViewPool: RecycledViewPool
    )

    class NestedViewHolder(
        override val binding: NestedItemViewHolderBinding,
        override val handleEvent: (UiEvent) -> Unit
    ) : VerticalViewHolder(binding, handleEvent) {

        private val horizontalAdapter by lazy { HorizontalAdapter(emptyList(), handleEvent) }

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
            item: VerticalItem,
            recycledViewPool: RecycledViewPool
        ) {
            with(binding) {
                bindingItem = item as VerticalItem.SuggestionItem
            }
        }
    }

    class SlidingViewHolder(
        override val binding: SlidingImageViewHolderBinding,
        override val handleEvent: (UiEvent) -> Unit
    ) : VerticalViewHolder(binding, handleEvent) {

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
            item: VerticalItem,
            recycledViewPool: RecycledViewPool
        ) {
            item as VerticalItem.SliderItem
            adapter.submitList(item.data)

            if (item.data.isEmpty()) {
                binding.imageSlider.stopAutoCycle()
            } else {
                binding.imageSlider.startAutoCycle()
            }
        }
    }
}