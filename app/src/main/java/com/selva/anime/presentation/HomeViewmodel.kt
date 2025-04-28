package com.selva.anime.presentation

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selva.anime.presentation.constants.AnimeConstant
import com.selva.anime.presentation.data.VerticalItem
import com.selva.anime.presentation.event.AnimeEvent
import com.selva.anime.presentation.model.AnimeModel
import com.selva.anime.ui.detail.AnimeDetailActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(private val model: AnimeModel) : ViewModel() {
    val loadingLiveData: LiveData<Boolean>
        get() = model.loadingLiveData

    val errorLiveData: LiveData<String?>
        get() = model.errorMessageLiveDate

    val animeCategoryLiveData: LiveData<List<VerticalItem>>
        get() = model.animeCategoryLiveData

    fun fetchAnime() {
        viewModelScope.launch(Dispatchers.IO) {
            model.loadData()
        }
    }

    fun handleEvent(event: AnimeEvent, context: Context) {
        when (event) {
            is AnimeEvent.SelectAnime -> {
                Intent(context, AnimeDetailActivity::class.java).apply {
                    putExtra(AnimeConstant.ANIME_ID, event.id)
                }.also {
                    context.startActivity(it)
                }
            }
        }
    }
}