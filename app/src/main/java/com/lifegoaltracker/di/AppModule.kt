package com.lifegoaltracker.di

import android.app.Application
import android.arch.persistence.room.Room
import com.lifegoaltracker.repository.Database
import com.lifegoaltracker.repository.vision.VisionDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideDb(app: Application): Database {
        return Room
                .databaseBuilder(app, Database::class.java, "database.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideVisionDao(database: Database): VisionDao {
        return database.visionDao()
    }
}