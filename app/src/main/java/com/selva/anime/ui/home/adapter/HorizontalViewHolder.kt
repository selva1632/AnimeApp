package com.selva.anime.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selva.anime.databinding.AnimeCategoryItemViewHolderBinding
import com.selva.anime.presentation.data.HorizontalItem
import com.selva.anime.presentation.event.AnimeEvent
import com.selva.anime.ui.home.eventlistener.AnimeClickListener

class HorizontalViewHolder(
    val binding: AnimeCategoryItemViewHolderBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: HorizontalItem.NestedItem, eventListener: AnimeClickListener) {
        with(binding) {
            bindingItem = item

            Glide.with(animeImageView.context)
                .load(item.imageUrl)
                .centerInside()
                .into(animeImageView)

            animeImageView.setOnClickListener {
                item.id?.let { it1 ->
                    eventListener.sendEvent(AnimeEvent.SelectAnime(it1))
                }
            }
        }
    }
}