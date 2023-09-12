package com.example.data.local.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.models.CharacterDbModel

@Database(entities = [CharacterDbModel::class], version = 1, exportSchema = false)
@TypeConverters(LocationTypeConverter::class)
abstract class AppDataBase : RoomDatabase() {
    companion object {

        private var INSTANCE: AppDataBase? = null
        private const val DB_NAME = "character.db"
        private val LOCK = Any()

        fun getInstance(application: Application): AppDataBase {
            synchronized(LOCK) {
                INSTANCE?.let { return it }
                val db = Room.databaseBuilder(
                    application,
                    AppDataBase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }

    abstract fun characterDao(): CharacterDao
}