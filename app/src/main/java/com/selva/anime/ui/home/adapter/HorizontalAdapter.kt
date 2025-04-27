package com.selva.anime.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selva.anime.databinding.AnimeCategoryItemViewHolderBinding
import com.selva.anime.domain.model.AnimeItem
import com.selva.anime.ui.home.eventlistener.AnimeClickListener

class HorizontalAdapter(
    private var item: List<AnimeItem>,
    private val eventListener: AnimeClickListener
) : RecyclerView.Adapter<HorizontalViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(data: List<AnimeItem>) {
        item = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        return HorizontalViewHolder(
            AnimeCategoryItemViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                null,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        holder.bind(item[position], eventListener)
        holder.binding.executePendingBindings()
    }

    override fun onViewRecycled(holder: HorizontalViewHolder) {
        super.onViewRecycled(holder)
        Glide.with(holder.itemView.context).clear(holder.itemView)
    }
}