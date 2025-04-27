package com.selva.anime.di

import android.content.Context
import androidx.room.Room
import com.selva.anime.data.local.dao.AnimeDao
import com.selva.anime.data.local.database.AnimeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AnimeDatabase {
        return Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            "anime_db"
        ).build()
    }

    @Provides
    fun provideAnimeInsertDao(db: AnimeDatabase): AnimeDao {
        return db.animeInsertDao
    }
}