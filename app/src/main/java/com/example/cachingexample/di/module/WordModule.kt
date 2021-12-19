package com.example.cachingexample.di.module

import android.app.Application
import androidx.room.Room
import com.example.cachingexample.feature.data.data_source.local.TypeConverter
import com.example.cachingexample.feature.data.data_source.local.WordInfoDatabase
import com.example.cachingexample.feature.data.data_source.reomte.DictionaryApi
import com.example.cachingexample.feature.data.repository.WordInfoRepository
import com.example.cachingexample.feature.data.util.GsonParser
import com.example.cachingexample.feature.domain.repository.WordInfoRepositoryImpl
import com.example.cachingexample.feature.domain.use_case.GetWordInfo
import com.example.cachingexample.util.Constants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordModule {

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase{

        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            Constants.DATABASE_NAME
        ).addTypeConverter(TypeConverter(GsonParser(Gson()))).build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi{

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(api: DictionaryApi,db: WordInfoDatabase): WordInfoRepository{

        return WordInfoRepositoryImpl(api,db.wordInfoDao)
    }

    @Provides
    @Singleton
    fun provideGetWordInfo(repository: WordInfoRepository): GetWordInfo{
        return GetWordInfo(repository)
    }
}