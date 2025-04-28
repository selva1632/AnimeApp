package com.selva.anime.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selva.anime.domain.model.toDetail
import com.selva.anime.domain.usecase.AnimeUseCase
import com.selva.anime.domain.utils.network.wrapper.Result
import com.selva.anime.presentation.data.DetailData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val useCase: AnimeUseCase) : ViewModel() {

    var detailInfo = MutableLiveData<DetailData>()
        private set

    fun fetchAnimeById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = useCase.getAnimeById(id)) {
                is Result.Error -> {
                    Log.e(TAG, "error - ${result.message.toString()}")
                }

                is Result.Success -> {
                    result.data.let {
                        detailInfo.postValue(it.toDetail())
                    }
                }
            }

        }
    }

    companion object {
        const val TAG = "DetailViewModel"
    }
}