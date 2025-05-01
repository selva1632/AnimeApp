package com.selva.anime.ui.home.adapter.ext

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.selva.anime.presentation.data.HorizontalItem
import com.selva.anime.presentation.data.VerticalItem
import com.selva.anime.ui.home.contract.UiState
import com.selva.anime.ui.home.adapter.HorizontalAdapter
import com.selva.anime.ui.home.adapter.VerticalAdapter

@BindingAdapter("app:visibility")
fun setVisibility(progressBar: ProgressBar, state: UiState<List<VerticalItem>>?) {
    progressBar.visibility = if (state is UiState.Loading) View.VISIBLE else View.GONE
}

@BindingAdapter("bind:NestedItem")
fun bindNestedItem(recyclerView: RecyclerView, items: List<HorizontalItem.NestedItem>?) {
    val adapter = recyclerView.adapter as HorizontalAdapter
    items?.let {
        adapter.submitList(it)
    }
}

@BindingAdapter("bind:VerticalItem")
fun bindVerticalItem(recyclerView: RecyclerView, state: UiState<List<VerticalItem>>?) {
    val adapter = recyclerView.adapter as VerticalAdapter
    if (state is UiState.Success) {
        adapter.submitList(state.data)
    }
}