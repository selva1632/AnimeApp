package com.selva.anime.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.selva.anime.data.local.dao.AnimeDao
import com.selva.anime.data.local.model.AnimeEntity

@Database(
    entities = [AnimeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AnimeDatabase: RoomDatabase() {
    abstract val animeInsertDao: AnimeDao
}