package com.selva.anime.presentation.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.selva.anime.domain.usecase.AnimeUseCase
import com.selva.anime.presentation.constants.type.SeasonType
import com.selva.anime.presentation.constants.type.YearType
import com.selva.anime.presentation.data.VerticalItem
import com.selva.anime.domain.model.toSeasons
import com.selva.anime.domain.model.toSlider
import com.selva.anime.domain.utils.network.wrapper.Result
import javax.inject.Inject

class AnimeModel @Inject constructor(private val useCase: AnimeUseCase) {
    private val animeCategoryItem = mutableListOf<VerticalItem>()

    private val _loadingLiveData = MutableLiveData(true)
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    private val _animeCategoryLiveData = MutableLiveData<List<VerticalItem>>(emptyList())
    val animeCategoryLiveData: LiveData<List<VerticalItem>>
        get() = _animeCategoryLiveData

    private val _errorMessageLiveDate = MutableLiveData<String?>(null)
    val errorMessageLiveDate: LiveData<String?>
        get() = _errorMessageLiveDate

    suspend fun loadData() {
        _loadingLiveData.postValue(true)
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
        _loadingLiveData.postValue(false)
        _animeCategoryLiveData.postValue(animeCategoryItem)
    }

    companion object {
        private const val TAG = "AnimeModel"
    }
}