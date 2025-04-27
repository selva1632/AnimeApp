package com.selva.anime.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selva.anime.domain.model.AnimeSectionItem
import com.selva.anime.presentation.AnimeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewmodel @Inject constructor(private val model: AnimeModel) : ViewModel() {
    val loadingLiveData: LiveData<Boolean>
        get() = model.loadingLiveData

    val errorLiveData: LiveData<String?>
        get() = model.errorMessageLiveDate

    val animeCategoryLiveData: LiveData<List<AnimeSectionItem>>
        get() = model.animeCategoryLiveData

    fun fetchAnime() {
        viewModelScope.launch(Dispatchers.IO) {
            model.loadData()
        }
    }
}