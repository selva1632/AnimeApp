package com.selva.anime.ui.home.ext

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.selva.anime.presentation.data.HorizontalItem
import com.selva.anime.presentation.data.VerticalItem
import com.selva.anime.ui.home.adapter.HorizontalAdapter
import com.selva.anime.ui.home.adapter.VerticalAdapter

@BindingAdapter("bind:VerticalItem")
fun bindVerticalItem(recyclerView: RecyclerView, items: List<VerticalItem>?) {
    val adapter = recyclerView.adapter as VerticalAdapter
    items?.let {
        adapter.submitList(it)
    }
}

@BindingAdapter("bind:NestedItem")
fun bindNestedItem(recyclerView: RecyclerView, items: List<HorizontalItem.NestedItem>?) {
    val adapter = recyclerView.adapter as HorizontalAdapter
    items?.let {
        adapter.submitList(it)
    }
}

@BindingAdapter("app:visibility")
fun setVisibility(progressBar: ProgressBar, isLoading: Boolean?) {
    progressBar.visibility = if (isLoading == true) View.VISIBLE else View.GONE
}