package com.selva.anime.presentation.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.selva.anime.domain.model.toSeasons
import com.selva.anime.domain.model.toSlider
import com.selva.anime.domain.usecase.AnimeUseCase
import com.selva.anime.domain.utils.network.wrapper.Result
import com.selva.anime.presentation.constants.type.SeasonType
import com.selva.anime.presentation.constants.type.YearType
import com.selva.anime.presentation.data.VerticalItem
import com.selva.anime.ui.home.contract.UiState
import javax.inject.Inject

class AnimeModel @Inject constructor(private val useCase: AnimeUseCase) {
    private val animeCategoryItem = mutableListOf<VerticalItem>()

    private val _errorMessageLiveDate = MutableLiveData<String>()
    val errorMessageLiveDate: LiveData<String>
        get() = _errorMessageLiveDate

    private val _uiStateLiveData = MutableLiveData<UiState<List<VerticalItem>>>()
    val uiStateLiveData: LiveData<UiState<List<VerticalItem>>>
        get() = _uiStateLiveData

    suspend fun loadData() {
        _uiStateLiveData.postValue(UiState.Loading)
        getAnimeByYear()
        getRecommendAnime()
        getTopAnime()
        postAnimeCategory()
    }

    private suspend fun getAnimeByYear() {
        getAnimeByYearAndSeason(YearType.YEAR_2024.year, SeasonType.FALL.type)
        getAnimeByYearAndSeason(YearType.YEAR_2023.year, SeasonType.SPRING.type)
        getAnimeByYearAndSeason(YearType.YEAR_2022.year, SeasonType.FALL.type)
        getAnimeByYearAndSeason(YearType.YEAR_2020.year, SeasonType.FALL.type)
        getAnimeByYearAndSeason(YearType.YEAR_2019.year, SeasonType.SPRING.type)
        getAnimeByYearAndSeason(YearType.YEAR_2018.year, SeasonType.FALL.type)
        getAnimeByYearAndSeason(YearType.YEAR_2017.year, SeasonType.SPRING.type)
        getAnimeByYearAndSeason(YearType.YEAR_2016.year, SeasonType.FALL.type)
        getAnimeByYearAndSeason(YearType.YEAR_2015.year, SeasonType.SPRING.type)
        getAnimeByYearAndSeason(YearType.YEAR_2014.year, SeasonType.FALL.type)
        getAnimeByYearAndSeason(YearType.YEAR_2013.year, SeasonType.SPRING.type)
        getAnimeByYearAndSeason(YearType.YEAR_2012.year, SeasonType.FALL.type)
        getAnimeByYearAndSeason(YearType.YEAR_2011.year, SeasonType.SPRING.type)
        getAnimeByYearAndSeason(YearType.YEAR_2010.year, SeasonType.FALL.type)
    }

    private suspend fun getTopAnime() {
        when (val result = useCase.getTopAnime()) {
            is Result.Success -> {
                result.data.let { item ->
                    animeCategoryItem.add(
                        0,
                        VerticalItem.SliderItem(
                            data = item.map { it.toSlider() }
                        )
                    )
                }
            }

            is Result.Error -> {
                Log.i(TAG, result.message.toString())
                _errorMessageLiveDate.postValue(result.message.toString())
            }
        }
    }

    private suspend fun getRecommendAnime() {
        when (val result = useCase.getRecommendAnime()) {
            is Result.Success -> {
                result.data.let { item ->
                    animeCategoryItem.add(
                        0,
                        VerticalItem.SuggestionItem(
                            id = 1000,
                            title = "Recommended",
                            data = item.map { it.toSeasons() }
                        )
                    )
                }
            }

            is Result.Error -> {
                Log.i(TAG, result.message.toString())
                _errorMessageLiveDate.postValue(result.message.toString())
            }
        }
    }

    private suspend fun getAnimeByYearAndSeason(
        year: Int,
        season: String
    ) {
        when (val result = useCase.getAnimeByYearAndSeason(year, season)) {
            is Result.Success -> {
                result.data.let { item ->
                    animeCategoryItem.add(
                        VerticalItem.SuggestionItem(
                            id = year,
                            title = year.toString(),
                            data = item.map { it.toSeasons() }
                        )
                    )
                }
            }

            is Result.Error -> {
                Log.i(TAG, result.message.toString())
                _errorMessageLiveDate.postValue(result.message.toString())
            }
        }
    }

    private fun postAnimeCategory() {
        _uiStateLiveData.postValue(UiState.Success(animeCategoryItem))
    }

    companion object {
        private const val TAG = "AnimeModel"
    }
}