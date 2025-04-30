package com.selva.anime.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selva.anime.databinding.AnimeCategoryItemViewHolderBinding
import com.selva.anime.presentation.data.HorizontalItem
import com.selva.anime.ui.home.contract.UiEvent

class HorizontalViewHolder(
    val binding: AnimeCategoryItemViewHolderBinding,
    val handleEvent: (UiEvent) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HorizontalItem.NestedItem) {
        with(binding) {
            bindingItem = item

            Glide.with(animeImageView.context)
                .load(item.imageUrl)
                .centerInside()
                .into(animeImageView)

            animeImageView.setOnClickListener {
                handleEvent(UiEvent.SelectAnime(item.id))
            }
        }
    }
}