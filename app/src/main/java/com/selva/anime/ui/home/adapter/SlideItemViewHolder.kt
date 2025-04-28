package com.selva.anime.ui.home.adapter

import com.bumptech.glide.Glide
import com.selva.anime.databinding.SlideItemImageBinding
import com.selva.anime.presentation.data.HorizontalItem
import com.smarteist.autoimageslider.SliderViewAdapter

class SlideItemViewHolder(
    val binding: SlideItemImageBinding
) : SliderViewAdapter.ViewHolder(binding.root) {

    fun bind(item: HorizontalItem.SliderData) {
        with(binding) {
            Glide.with(sliderImageView.context)
                .load(item.imageUrl)
                .into(sliderImageView)
        }
    }
}