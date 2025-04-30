package com.selva.anime.ui.home.adapter.ext

import androidx.recyclerview.widget.DiffUtil

class GenericAnimeDiffUtil<T>(
    val oldList: List<T>,
    val newList: List<T>,
    val areItemTheSame: (T, T) -> Boolean,
    val areContentTheSame: (T, T) -> Boolean
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        areItemTheSame(oldList[oldItemPosition], newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        areContentTheSame(oldList[oldItemPosition], newList[newItemPosition])

}