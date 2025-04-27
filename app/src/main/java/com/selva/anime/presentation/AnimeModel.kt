package com.selva.anime.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.selva.anime.domain.model.AnimeSectionItem
import com.selva.anime.domain.usecase.AnimeUseCase
import com.selva.anime.presentation.type.SeasonType
import com.selva.anime.presentation.type.YearType
import com.selva.anime.utils.network.wrapper.Result
import javax.inject.Inject

class AnimeModel @Inject constructor(private val useCase: AnimeUseCase) {
    private val animeCategoryItem = mutableListOf<AnimeSectionItem>()

    private val _loadingLiveData = MutableLiveData(true)
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    private val _animeCategoryLiveData = MutableLiveData<List<AnimeSectionItem>>(emptyList())
    val animeCategoryLiveData: LiveData<List<AnimeSectionItem>>
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
                result.data.let {
                    animeCategoryItem.add(
                        0,
                        AnimeSectionItem(
                            title = "Trending",
                            data = it
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
                result.data.let {
                    animeCategoryItem.add(
                        0,
                        AnimeSectionItem(
                            title = "Recommended",
                            data = it
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
                result.data.let {
                    animeCategoryItem.add(
                        AnimeSectionItem(
                            title = year.toString(),
                            data = it
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