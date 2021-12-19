package com.example.cachingexample.feature.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cachingexample.feature.data.data_source.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class WordInfoDatabase: RoomDatabase() {

    abstract val wordInfoDao: WordInfoDao
}