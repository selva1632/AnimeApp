package com.selva.anime.ui.home.ext

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.selva.anime.domain.model.AnimeItem
import com.selva.anime.domain.model.AnimeSectionItem
import com.selva.anime.ui.home.adapter.HorizontalAdapter
import com.selva.anime.ui.home.adapter.VerticalAdapter

@BindingAdapter("bind:AnimeSelectionItems")
fun bindAnimeSelectionItems(recyclerView: RecyclerView, items: List<AnimeSectionItem>?) {
    val adapter = recyclerView.adapter as VerticalAdapter
    items?.let {
        adapter.submitList(it)
    }
}

@BindingAdapter("bind:AnimeItems")
fun bindAnimeItems(recyclerView: RecyclerView, items: List<AnimeItem>?) {
    val adapter = recyclerView.adapter as HorizontalAdapter
    items?.let {
        adapter.submitList(it)
    }
}

@BindingAdapter("app:visibility")
fun setVisibility(progressBar: ProgressBar, isLoading: Boolean?) {
    progressBar.visibility = if (isLoading == true) View.VISIBLE else View.GONE
}