package com.selva.anime.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.selva.anime.databinding.SlideItemImageBinding
import com.selva.anime.presentation.data.HorizontalItem
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter : SliderViewAdapter<SlideItemViewHolder>() {

    private val animeItem = mutableListOf<HorizontalItem.SliderData>()

    fun submitList(items: List<HorizontalItem.SliderData>) {
        animeItem.clear()
        animeItem.addAll(items)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return animeItem.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SlideItemViewHolder {
        return SlideItemViewHolder(
            SlideItemImageBinding.inflate(LayoutInflater.from(parent?.context), null, false)
        )
    }

    override fun onBindViewHolder(viewHolder: SlideItemViewHolder?, position: Int) {
        viewHolder?.let {
            it.bind(animeItem[position])
            it.binding.executePendingBindings()
        }
    }

}