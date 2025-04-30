package com.selva.anime.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selva.anime.presentation.data.VerticalItem
import com.selva.anime.ui.home.contract.UiState
import com.selva.anime.ui.home.contract.UiEvent
import com.selva.anime.presentation.model.AnimeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(private val model: AnimeModel) : ViewModel() {

    private val _eventLiveDate = MutableLiveData<UiEvent>()
    val eventLiveData = _eventLiveDate

    val errorLiveData: LiveData<String>
        get() = model.errorMessageLiveDate

    val uiStateLiveData: LiveData<UiState<List<VerticalItem>>>
        get() = model.uiStateLiveData

    fun fetchAnime() {
        viewModelScope.launch(Dispatchers.IO) {
            model.loadData()
        }
    }

    fun handleEvent(event: UiEvent) {
        when (event) {
            is UiEvent.SelectAnime -> {
                _eventLiveDate.value = event
            }
        }
    }
}