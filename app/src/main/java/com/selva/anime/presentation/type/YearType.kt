package com.selva.anime.presentation.type

enum class YearType(val year: Int) {
    YEAR_2024(2024),
    YEAR_2023(2023),
    YEAR_2022(2022),
    YEAR_2021(2021),
    YEAR_2020(2020),
    YEAR_2019(2019),
    YEAR_2018(2018),
    YEAR_2017(2017),
    YEAR_2016(2016),
    YEAR_2015(2015),
    YEAR_2014(2014),
    YEAR_2013(2013),
    YEAR_2012(2012),
    YEAR_2011(2011),
    YEAR_2010(2010);

    companion object {
        fun getTypeByYear(year: Int): YearType {
            return entries.first { it.year == year }
        }
    }
}