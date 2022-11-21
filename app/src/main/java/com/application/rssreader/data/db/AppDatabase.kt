package com.application.rssreader.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.application.rssreader.data.model.RSSFeedEntity
import com.application.rssreader.data.util.DATABASE_NAME
import com.sevenpeakssoftware.sultan.data.db.RSSFeedsDAO

@Database(
    entities = [RSSFeedEntity::class],
    version = 1, exportSchema = false

)

abstract class AppDatabase : RoomDatabase() {
    abstract fun getRSSFeedsDAO(): RSSFeedsDAO

    companion object {

        private var INSTANCE: AppDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, DATABASE_NAME
                    ).fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
}

